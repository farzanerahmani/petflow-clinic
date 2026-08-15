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
public class AdjustmentStockCommand extends TransactionCommand {

    private Long warehouseId;

    private Long drugId;

    /**
     * موجودی جدید بعد از Adjustment
     */
    private BigDecimal newQuantity;

    /**
     * علت اصلاح موجودی
     */
    private String reason;
}
