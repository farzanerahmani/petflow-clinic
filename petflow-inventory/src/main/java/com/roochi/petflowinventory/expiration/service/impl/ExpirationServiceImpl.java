package com.roochi.petflowinventory.expiration.service.impl;

import com.roochi.petflowinventory.expiration.dto.response.ExpirationStockResponseDto;
import com.roochi.petflowinventory.expiration.repository.ExpirationRepository;
import com.roochi.petflowinventory.expiration.service.ExpirationService;
import com.roochi.petflowinventory.inventoryread.service.InventoryReadService;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpirationServiceImpl
        implements ExpirationService {


    private final ExpirationRepository expirationRepository;


    @Override
    public List<ExpirationStockResponseDto> findExpired() {


        return expirationRepository
                .findExpiredStocks(
                        LocalDate.now()
                )
                .stream()
                .map(this::map)
                .toList();
    }


    @Override
    public List<ExpirationStockResponseDto> findNearExpiration(
            int days
    ) {

        LocalDate now =
                LocalDate.now();


        return expirationRepository
                .findNearExpirationStocks(
                        now,
                        now.plusDays(days)
                )
                .stream()
                .map(this::map)
                .toList();
    }



    private ExpirationStockResponseDto map(
            Stock stock
    ) {

        return ExpirationStockResponseDto.builder()

                .stockId(
                        stock.getId()
                )

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

                .quantity(
                        stock.getQuantity()
                )

                .availableQuantity(
                        stock.getAvailableQuantity()
                )

                .build();
    }
}