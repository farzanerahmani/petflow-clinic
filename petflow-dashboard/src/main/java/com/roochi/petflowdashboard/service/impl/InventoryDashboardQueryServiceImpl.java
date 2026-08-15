package com.roochi.petflowdashboard.service.impl;

import com.roochi.petflowdashboard.dto.response.ExpiringStockDto;
import com.roochi.petflowdashboard.dto.response.InventoryDashboardDto;
import com.roochi.petflowdashboard.dto.response.LowStockDto;
import com.roochi.petflowdashboard.dto.response.OutOfStockDto;
import com.roochi.petflowdashboard.repository.InventoryDashboardRepository;
import com.roochi.petflowdashboard.repository.ReservationDashboardRepository;
import com.roochi.petflowdashboard.service.InventoryDashboardQueryService;
import com.roochi.petflowinventory.reservation.entity.enums.ReservationStatus;
import com.roochi.petflowinventory.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryDashboardQueryServiceImpl
        implements InventoryDashboardQueryService {

    private final InventoryDashboardRepository inventoryDashboardRepository;

    private final ReservationDashboardRepository
            reservationDashboardRepository;

    @Override
    public InventoryDashboardDto getSummary(
            Long clinicId
    ) {

        long lowStock =
                inventoryDashboardRepository
                        .countLowStock(clinicId);

        long outOfStock =
                inventoryDashboardRepository
                        .countOutOfStock(clinicId);

        long activeReservations =
                reservationDashboardRepository
                        .countByStatus(
                                clinicId,
                                ReservationStatus.RESERVED
                        );

        return InventoryDashboardDto.builder()
                .lowStock(lowStock)
                .outOfStock(outOfStock)
                .activeReservations(activeReservations)
                .build();
    }

    @Override
    public List<LowStockDto> getLowStock(
            Long clinicId
    ) {

        return inventoryDashboardRepository
                .findLowStock(clinicId)
                .stream()
                .map(this::toLowStockDto)
                .toList();
    }


    @Override
    public List<OutOfStockDto> getOutOfStock(
            Long clinicId
    ) {

        return inventoryDashboardRepository
                .findOutOfStock(clinicId)
                .stream()
                .map(this::toOutOfStockDto)
                .toList();
    }


    @Override
    public List<ExpiringStockDto> getExpiringSoon(
            Long clinicId,
            int days
    ) {

        if (days < 0) {
            throw new IllegalArgumentException(
                    "Days cannot be negative."
            );
        }

        LocalDate today = LocalDate.now();

        LocalDate expirationDate =
                today.plusDays(days);

        return inventoryDashboardRepository
                .findExpiringSoon(
                        clinicId,
                        today,
                        expirationDate
                )
                .stream()
                .map(this::toExpiringStockDto)
                .toList();
    }


    private LowStockDto toLowStockDto(Stock stock) {

        return LowStockDto.builder()
                .stockId(stock.getId())
                .drugId(stock.getDrug().getId())
                .drugName(stock.getDrug().getBrandName())
                .warehouseId(stock.getWarehouse().getId())
                .batchNumber(stock.getBatchNumber())
                .quantity(stock.getQuantity())
                .reservedQuantity(
                        stock.getReservedQuantity()
                )
                .availableQuantity(
                        stock.getAvailableQuantity()
                )
                .minimumQuantity(
                        stock.getMinimumQuantity()
                )
                .build();
    }


    private OutOfStockDto toOutOfStockDto(Stock stock) {

        return OutOfStockDto.builder()
                .stockId(stock.getId())
                .drugId(stock.getDrug().getId())
                .drugName(stock.getDrug().getBrandName())
                .warehouseId(stock.getWarehouse().getId())
                .batchNumber(stock.getBatchNumber())
                .quantity(stock.getQuantity())
                .reservedQuantity(
                        stock.getReservedQuantity()
                )
                .availableQuantity(
                        stock.getAvailableQuantity()
                )
                .build();
    }


    private ExpiringStockDto toExpiringStockDto(
            Stock stock
    ) {

        return ExpiringStockDto.builder()
                .stockId(stock.getId())
                .drugId(stock.getDrug().getId())
                .drugName(stock.getDrug().getBrandName())
                .warehouseId(stock.getWarehouse().getId())
                .batchNumber(stock.getBatchNumber())
                .expirationDate(
                        stock.getExpirationDate()
                )
                .quantity(stock.getQuantity())
                .reservedQuantity(
                        stock.getReservedQuantity()
                )
                .availableQuantity(
                        stock.getAvailableQuantity()
                )
                .build();
    }
}
