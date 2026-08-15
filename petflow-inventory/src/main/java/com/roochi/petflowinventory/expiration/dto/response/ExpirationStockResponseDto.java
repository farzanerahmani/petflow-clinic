package com.roochi.petflowinventory.expiration.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/2/2026
 */

@Getter
@Builder
public class ExpirationStockResponseDto {

    private Long stockId;

    private Long warehouseId;

    private Long drugId;

    private String batchNumber;

    private LocalDate expirationDate;

    private BigDecimal quantity;

    private BigDecimal availableQuantity;
}