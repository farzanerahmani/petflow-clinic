package com.roochi.petflowinventory.stock.model;

import com.roochi.petflowinventory.stock.entity.Stock;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StockSnapshot {

    private final BigDecimal quantityBefore;

    private final BigDecimal quantityAfter;

    private final BigDecimal reservedQuantityBefore;

    private final BigDecimal reservedQuantityAfter;

    /**
     * Snapshot before changing stock
     */
    public static StockSnapshot before(Stock stock) {

        return new StockSnapshot(
                stock.getQuantity(),
                null,
                stock.getReservedQuantity(),
                null
        );
    }

    /**
     * Build final snapshot after changing stock
     */
    public StockSnapshot after(Stock stock) {

        return new StockSnapshot(
                quantityBefore,
                stock.getQuantity(),
                reservedQuantityBefore,
                stock.getReservedQuantity()
        );
    }
}
