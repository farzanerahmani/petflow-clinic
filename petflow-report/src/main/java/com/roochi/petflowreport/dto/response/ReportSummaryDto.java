package com.roochi.petflowreport.dto.response;

import lombok.*;

import java.math.BigDecimal;
/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSummaryDto {

    private Long totalRecords;

    private BigDecimal totalQuantity;

    private BigDecimal totalAmount;

    private BigDecimal averageAmount;
}
