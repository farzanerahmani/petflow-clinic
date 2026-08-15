package com.roochi.petflowinventory.stock.command;

import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowvisit.drug.entity.Drug;
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
public class IncreaseStockCommand extends TransactionCommand {

    private Long warehouseId;

    private Long drugId;

    /**
     * قیمت واحد خرید
     */
    private BigDecimal unitCost;

    /**
     * حداقل موجودی
     */
    private BigDecimal minimumQuantity;
}
