package com.roochi.petflowinventory.stock.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseDto {

    private Long id;

    private Long warehouseId;

    private String warehouseName;

    private Long drugId;

    private String drugName;

    private String batchNumber;

    private LocalDate expirationDate;

    private BigDecimal quantity;

    private BigDecimal reservedQuantity;

    private BigDecimal availableQuantity;

    private BigDecimal minimumQuantity;

    private BigDecimal averageUnitCost;
}
