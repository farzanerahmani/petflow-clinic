package com.roochi.petflowinventory.stock.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Getter
@Setter
public class ReleaseReservationCommand extends TransactionCommand {
    private Long stockId;
    private Long warehouseId;


    private Long drugId;

}
