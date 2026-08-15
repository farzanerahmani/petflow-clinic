package com.roochi.petflowinventory.stock.domain;

import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.model.StockAllocation;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowinventory.warehouse.repository.WarehouseRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.drug.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Component
@RequiredArgsConstructor
public class StockAllocator {

    private final StockRepository stockRepository;

    @Transactional
    public List<StockAllocation> allocate(
            Long warehouseId,
            Long drugId,
            BigDecimal requestedQuantity
    ) {

        if (requestedQuantity == null ||
                requestedQuantity.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Requested quantity must be greater than zero."
            );
        }

        List<Stock> stocks =
                stockRepository.findAvailableStocksOrderByExpirationForUpdate(
                        warehouseId,
                        drugId
                );

        BigDecimal remaining = requestedQuantity;

        List<StockAllocation> allocations =
                new ArrayList<>();

        for (Stock stock : stocks) {

            if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }

            BigDecimal available =
                    stock.getAvailableQuantity();

            if (available.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            BigDecimal allocated =
                    available.min(remaining);

            allocations.add(
                    StockAllocation.builder()
                            .stockId(stock.getId())
                            .warehouseId(
                                    stock.getWarehouse().getId()
                            )
                            .drugId(
                                    stock.getDrug().getId()
                            )
                            .batchNumber(
                                    stock.getBatchNumber()
                            )
                            .expirationDate(
                                    stock.getExpirationDate()
                            )
                            .quantity(allocated)
                            .unitCost(
                                    stock.getAverageUnitCost()
                            )
                            .build()
            );

            remaining =
                    remaining.subtract(allocated);
        }

        if (remaining.compareTo(BigDecimal.ZERO) > 0) {

            throw new IllegalStateException(
                    "Insufficient available stock."
            );
        }

        return allocations;
    }
}