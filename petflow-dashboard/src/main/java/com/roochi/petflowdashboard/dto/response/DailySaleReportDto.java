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
public class DailySaleReportDto {

    private LocalDate date;

    private long saleCount;

    private BigDecimal totalAmount;
}
