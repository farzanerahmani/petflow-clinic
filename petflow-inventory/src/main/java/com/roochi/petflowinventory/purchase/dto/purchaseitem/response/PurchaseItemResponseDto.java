package com.roochi.petflowinventory.purchase.dto.purchaseitem.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/28/2026
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemResponseDto {
    private Long id;
    private Long purchaseId;
    private Long drugId;
    private String drugCode;
    private String drugName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
    private String batchNumber;
    private LocalDate expirationDate;
}
