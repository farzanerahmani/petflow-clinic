package com.roochi.petflowinventory.stock.command;

import com.roochi.petflowinventory.warehouse.entity.Warehouse;
import com.roochi.petflowvisit.drug.entity.Drug;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Getter
@Setter
public abstract class WarehouseDrugCommand extends TransactionCommand {

    /**
     * Warehouse where stock should be deducted.
     */
    private Warehouse warehouse;

    /**
     * Drug to be sold.
     */
    private Drug drug;

    /**
     * Requested quantity to sell.
     */
    private BigDecimal quantity;
}
