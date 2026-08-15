package com.roochi.petflowinventory.reservation.service;

import com.roochi.petflowinventory.reservation.entity.Reservation;
import com.roochi.petflowinventory.reservation.entity.ReservationItem;
import com.roochi.petflowinventory.reservation.repository.ReservationItemRepository;
import com.roochi.petflowinventory.service.InventoryTransactionService;
import com.roochi.petflowinventory.stock.command.ReservationCommand;
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
public class ReservationInventorySynchronizer {

    private final InventoryTransactionService inventoryTransactionService;

    @Transactional
    public void reserve(
            Reservation reservation,
            Long drugId,
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

        ReservationCommand command =
                new ReservationCommand();

        command.setWarehouseId(
                reservation.getWarehouse().getId()
        );

        command.setDrugId(drugId);

        command.setQuantity(quantity);

        command.setReferenceId(
                reservation.getId()
        );

        command.setReferenceNumber(
                reservation.getReservationNumber()
        );

        command.setDescription(
                reservation.getDescription()
        );

        command.setPerformedBy(
                performedBy
        );

        inventoryTransactionService.reserve(command);
    }
}
