package com.roochi.petflowdashboard.dto.response;

import lombok.*;

import java.math.BigDecimal;
/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopPurchasedDrugDto {

    private Long drugId;

    private String drugName;

    private BigDecimal totalQuantity;

    private BigDecimal totalAmount;
}
