package com.roochi.petflowinventory.stock.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Getter
@Setter
public class ReservationCommand extends TransactionCommand {

    private Long warehouseId;


    private Long drugId;


}
