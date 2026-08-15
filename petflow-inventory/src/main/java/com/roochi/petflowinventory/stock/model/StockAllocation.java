package com.roochi.petflowinventory.stock.model;

import com.roochi.petflowinventory.stock.entity.Stock;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/31/2026
 */
@Getter
@Builder
@AllArgsConstructor
public class StockAllocation {

    private final Long stockId;

    private final Long warehouseId;

    private final Long drugId;

    private final String batchNumber;

    private final java.time.LocalDate expirationDate;

    private final BigDecimal quantity;

    private final BigDecimal unitCost;
}


