package com.roochi.petflowdashboard.service;

import com.roochi.petflowdashboard.dto.response.DailySaleReportDto;
import com.roochi.petflowdashboard.dto.response.SaleDashboardDto;
import com.roochi.petflowdashboard.dto.response.TopSellingDrugDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface SaleDashboardQueryService {

    SaleDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    );

    List<DailySaleReportDto> getDailyReport(
            Long clinicId,
            LocalDate from,
            LocalDate to
    );

    List<TopSellingDrugDto> getTopSellingDrugs(
            Long clinicId,
            LocalDate from,
            LocalDate to,
            int limit
    );
}
