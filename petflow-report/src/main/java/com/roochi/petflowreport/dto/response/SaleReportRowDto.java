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
public class SaleReportRowDto {

    private LocalDate saleDate;

    private String saleNumber;

    private String drugName;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;


}
