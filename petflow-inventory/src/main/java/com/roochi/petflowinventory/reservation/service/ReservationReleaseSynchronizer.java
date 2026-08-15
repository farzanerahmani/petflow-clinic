package com.roochi.petflowinventory.reservation.service;

import com.roochi.petflowinventory.reservation.entity.Reservation;
import com.roochi.petflowinventory.reservation.entity.ReservationItem;
import com.roochi.petflowinventory.reservation.repository.ReservationItemRepository;
import com.roochi.petflowinventory.service.InventoryTransactionService;
import com.roochi.petflowinventory.stock.command.ReleaseReservationCommand;
import com.roochi.petflowinventory.stocktransaction.entity.enums.StockReferenceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/6/2026
 */

@Component
@RequiredArgsConstructor
public class ReservationReleaseSynchronizer {

    private final InventoryTransactionService inventoryTransactionService;

    @Transactional
    public void release(
            Reservation reservation,
            Long drugId,
            String batchNumber,
            java.time.LocalDate expirationDate,
            BigDecimal quantity,
            String performedBy
    ) {

        if (reservation == null) {
            throw new IllegalArgumentException(
                    "Reservation must not be null."
            );
        }

        if (drugId == null) {
            throw new IllegalArgumentException(
                    "Drug id must not be null."
            );
        }

        if (quantity == null ||
                quantity.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }

        ReleaseReservationCommand command =
                new ReleaseReservationCommand();

        command.setWarehouseId(
                reservation.getWarehouse().getId()
        );

        command.setDrugId(drugId);

        command.setBatchNumber(batchNumber);

        command.setExpirationDate(expirationDate);

        command.setQuantity(quantity);

        command.setReferenceType(
                StockReferenceType.RESERVATION
        );

        command.setReferenceId(
                reservation.getId()
        );

        command.setReferenceNumber(
                reservation.getReservationNumber()
        );

        command.setDescription(
                "Release reservation: "
                        + reservation.getReservationNumber()
        );

        command.setPerformedBy(
                performedBy
        );

        inventoryTransactionService.releaseReservation(
                command
        );
    }
}