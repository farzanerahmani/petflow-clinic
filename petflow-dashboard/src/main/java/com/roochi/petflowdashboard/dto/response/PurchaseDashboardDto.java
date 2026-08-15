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
public class PurchaseDashboardDto {

    private long count;

    private BigDecimal totalAmount;
}
