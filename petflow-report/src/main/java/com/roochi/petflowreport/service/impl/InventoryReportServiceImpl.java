package com.roochi.petflowreport.service.impl;

import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.InventoryReportRowDto;
import com.roochi.petflowreport.repository.InventoryReportRepository;
import com.roochi.petflowreport.service.InventoryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryReportServiceImpl
        implements InventoryReportService {

    private final InventoryReportRepository inventoryReportRepository;

    @Override
    public List<InventoryReportRowDto> getInventoryReport(
            Long clinicId,
            ReportDateRangeRequestDto request
    ) {

        Long warehouseId =
                request != null
                        ? request.getWarehouseId()
                        : null;

        Long drugId =
                request != null
                        ? request.getDrugId()
                        : null;

        return inventoryReportRepository
                .getInventoryReport(
                        warehouseId,
                        drugId
                )
                .stream()
                .map(this::toDto)
                .toList();
    }

    private InventoryReportRowDto toDto(Stock stock) {

        BigDecimal availableQuantity =
                stock.getAvailableQuantity();

        String status =
                determineStatus(stock, availableQuantity);

        return InventoryReportRowDto.builder()

                .stockId(
                        stock.getId()
                )

                .warehouseId(
                        stock.getWarehouse().getId()
                )

                .warehouseName(
                        stock.getWarehouse().getName()
                )

                .drugId(
                        stock.getDrug().getId()
                )

                .drugName(
                        stock.getDrug().getBrandName()
                )

                .batchNumber(
                        stock.getBatchNumber()
                )

                .expirationDate(
                        stock.getExpirationDate()
                )

                .quantity(
                        stock.getQuantity()
                )

                .reservedQuantity(
                        stock.getReservedQuantity()
                )

                .availableQuantity(
                        availableQuantity
                )

                .minimumQuantity(
                        stock.getMinimumQuantity()
                )

                .averageUnitCost(
                        stock.getAverageUnitCost()
                )

                .stockStatus(
                        status
                )

                .build();
    }

    private String determineStatus(
            Stock stock,
            BigDecimal availableQuantity
    ) {

        if (availableQuantity.compareTo(
                BigDecimal.ZERO
        ) <= 0) {

            return "OUT_OF_STOCK";
        }

        if (availableQuantity.compareTo(
                stock.getMinimumQuantity()
        ) <= 0) {

            return "LOW_STOCK";
        }

        return "AVAILABLE";
    }
}
