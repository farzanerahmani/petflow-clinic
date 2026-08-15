package com.roochi.petflowinventory.stock.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class SaleStockCommand extends TransactionCommand {

    private Long warehouseId;

    private Long drugId;

    private String batchNumber;

    private LocalDate expirationDate;

    private BigDecimal quantity;

    private BigDecimal unitCost;
}
