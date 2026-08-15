package com.roochi.petflowreport.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReportRowDto {

    private Long stockId;

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

    private String stockStatus;
}
