package com.roochi.petflowinventory.inventoryalert.service.impl;

import com.roochi.petflowinventory.inventoryalert.dto.InventoryAlertDto;
import com.roochi.petflowinventory.inventoryalert.enums.AlertType;
import com.roochi.petflowinventory.inventoryalert.service.InventoryAlertService;
import com.roochi.petflowinventory.inventoryread.service.InventoryReadService;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Service
@RequiredArgsConstructor
public class InventoryAlertServiceImpl implements InventoryAlertService {

    private final InventoryReadService inventoryReadService;

    @Override
    public List<InventoryAlertDto> getAlerts() {

        List<InventoryAlertDto> result = new ArrayList<>();

        for (Stock stock : inventoryReadService.findLowStocks()) {

            result.add(build(stock,
                    AlertType.LOW_STOCK,
                    "Stock is below minimum quantity."));
        }

        for (Stock stock :
                inventoryReadService.findNearExpirationStocks(30)) {

            result.add(build(stock,
                    AlertType.NEAR_EXPIRATION,
                    "Stock is nearing expiration."));
        }

        for (Stock stock :
                inventoryReadService.findExpiredStocks()) {

            result.add(build(stock,
                    AlertType.EXPIRED,
                    "Stock has expired."));
        }

        return result;
    }

    private InventoryAlertDto build(
            Stock stock,
            AlertType type,
            String message) {

        return InventoryAlertDto.builder()
                .type(type)
                .warehouseId(stock.getWarehouse().getId())
                .warehouseName(stock.getWarehouse().getName())
                .drugId(stock.getDrug().getId())
                .drugName(stock.getDrug().getBrandName())
                .batchNumber(stock.getBatchNumber())
                .message(message)
                .build();
    }
}
