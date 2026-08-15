package com.roochi.petflowreport.dto.request;

import lombok.*;

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
public class ReportDateRangeRequestDto {

    private LocalDate from;

    private LocalDate to;

    private Long warehouseId;

    private Long drugId;
}
