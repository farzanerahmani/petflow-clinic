package com.roochi.petflowinventory.stock.domain;

import com.roochi.petflowinventory.purchase.dto.purchase.response.PurchaseSummaryDto;
import com.roochi.petflowinventory.purchase.entity.Purchase;
import com.roochi.petflowinventory.stock.command.IncreaseStockCommand;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.drug.entity.Drug;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Component
@RequiredArgsConstructor
public class StockFinder {

    private final StockRepository stockRepository;

    public Stock findById(Long stockId) {

        return stockRepository.findByIdForUpdate(stockId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.INTERNAL_ERROR));
//        /* StockError.STOCK_NOT_FOUND */));
    }

    /**
     * Find stock by warehouse, drug, batch and expiration date.
     */
    public Optional<Stock> find(IncreaseStockCommand command) {

        return stockRepository
                .findByWarehouseIdAndDrugIdAndBatchNumberAndExpirationDateAndDeletedFalse(
                        command.getWarehouseId(),
                        command.getDrugId(),
                        command.getBatchNumber(),
                        command.getExpirationDate()
                );
    }

}