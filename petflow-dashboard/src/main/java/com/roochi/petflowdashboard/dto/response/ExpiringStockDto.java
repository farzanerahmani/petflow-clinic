package com.roochi.petflowdashboard.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpiringStockDto {

    private Long stockId;

    private Long drugId;

    private String drugName;

    private Long warehouseId;

    private String batchNumber;

    private LocalDate expirationDate;

    private BigDecimal quantity;

    private BigDecimal reservedQuantity;

    private BigDecimal availableQuantity;
}
