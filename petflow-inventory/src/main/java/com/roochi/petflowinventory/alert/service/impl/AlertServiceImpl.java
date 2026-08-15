package com.roochi.petflowinventory.alert.service.impl;

import com.roochi.petflowinventory.alert.dto.request.ResolveAlertRequestDto;
import com.roochi.petflowinventory.alert.dto.response.AlertResponseDto;
import com.roochi.petflowinventory.alert.entity.Alert;
import com.roochi.petflowinventory.alert.entity.enums.AlertStatus;
import com.roochi.petflowinventory.alert.entity.enums.AlertType;
import com.roochi.petflowinventory.alert.repository.AlertRepository;
import com.roochi.petflowinventory.alert.service.AlertService;
import com.roochi.petflowinventory.stock.entity.Stock;
import com.roochi.petflowinventory.stock.repository.StockRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Service
@RequiredArgsConstructor
@Transactional
public class AlertServiceImpl implements AlertService {

    private static final int DEFAULT_EXPIRATION_WARNING_DAYS = 30;

    private final AlertRepository alertRepository;

    private final StockRepository stockRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<AlertResponseDto> findActive(
            Pageable pageable
    ) {

        return alertRepository
                .findAllByStatus(
                        AlertStatus.ACTIVE,
                        pageable
                )
                .map(this::map);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<AlertResponseDto> findByStatus(
            String status,
            Pageable pageable
    ) {

        AlertStatus alertStatus;

        try {
            alertStatus =
                    AlertStatus.valueOf(
                            status.toUpperCase()
                    );
        } catch (IllegalArgumentException e) {

            throw new IllegalArgumentException(
                    "Invalid alert status: " + status
            );
        }

        return alertRepository
                .findAllByStatus(
                        alertStatus,
                        pageable
                )
                .map(this::map);
    }


    @Override
    public AlertResponseDto resolve(
            ResolveAlertRequestDto request
    ) {

        Alert alert =
                alertRepository
                        .findActiveById(
                                request.getAlertId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (alert.getStatus() ==
                AlertStatus.RESOLVED) {

            throw new IllegalStateException(
                    "Alert is already resolved."
            );
        }

        alert.setStatus(
                AlertStatus.RESOLVED
        );

        alert.setResolvedAt(
                java.time.LocalDateTime.now()
        );

        alert.setResolvedBy(
                "system"
        );

        return map(alert);
    }


    @Override
    public void checkStock(
            Long stockId
    ) {

        Stock stock =
                stockRepository
                        .findByIdForUpdate(
                                stockId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        checkLowStock(stock);

        checkExpiration(stock);
    }


    private void checkLowStock(
            Stock stock
    ) {

        if (stock.getMinimumQuantity() == null) {
            return;
        }

        boolean lowStock =
                stock.getAvailableQuantity()
                        .compareTo(
                                stock.getMinimumQuantity()
                        ) < 0;

        if (!lowStock) {
            return;
        }

        createAlertIfNotExists(
                stock,
                AlertType.LOW_STOCK,
                "Stock quantity is below minimum quantity."
        );
    }


    private void checkExpiration(
            Stock stock
    ) {

        if (stock.getExpirationDate() == null) {
            return;
        }

        LocalDate today =
                LocalDate.now();

        if (stock.getExpirationDate()
                .isBefore(today)) {

            createAlertIfNotExists(
                    stock,
                    AlertType.EXPIRED,
                    "Stock batch has expired."
            );

            return;
        }

        if (!stock.getExpirationDate()
                .isAfter(
                        today.plusDays(
                                DEFAULT_EXPIRATION_WARNING_DAYS
                        )
                )) {

            createAlertIfNotExists(
                    stock,
                    AlertType.EXPIRING_SOON,
                    "Stock batch is close to expiration."
            );
        }
    }


    private void createAlertIfNotExists(
            Stock stock,
            AlertType type,
            String message
    ) {

        boolean exists =
                alertRepository
                        .existsByStockIdAndTypeAndStatusAndDeletedFalse(
                                stock.getId(),
                                type,
                                AlertStatus.ACTIVE
                        );

        if (exists) {
            return;
        }

        Alert alert =
                Alert.builder()
                        .stock(stock)
                        .type(type)
                        .status(AlertStatus.ACTIVE)
                        .message(message)
                        .alertDate(
                                java.time.LocalDateTime.now()
                        )
                        .build();

        alertRepository.save(alert);
    }


    private AlertResponseDto map(
            Alert alert
    ) {

        Stock stock =
                alert.getStock();

        return AlertResponseDto.builder()
                .id(alert.getId())
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
                .quantity(
                        stock.getQuantity()
                )
                .availableQuantity(
                        stock.getAvailableQuantity()
                )
                .type(alert.getType())
                .status(alert.getStatus())
                .message(alert.getMessage())
                .alertDate(alert.getAlertDate())
                .resolvedAt(alert.getResolvedAt())
                .resolvedBy(alert.getResolvedBy())
                .build();
    }
}
