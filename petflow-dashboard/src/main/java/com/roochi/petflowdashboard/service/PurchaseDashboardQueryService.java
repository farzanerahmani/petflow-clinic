package com.roochi.petflowdashboard.service;

import com.roochi.petflowdashboard.dto.response.DailyPurchaseReportDto;
import com.roochi.petflowdashboard.dto.response.PurchaseDashboardDto;
import com.roochi.petflowdashboard.dto.response.TopPurchasedDrugDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface PurchaseDashboardQueryService {

    PurchaseDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    );

    List<DailyPurchaseReportDto> getDailyReport(
            Long clinicId,
            LocalDate from,
            LocalDate to
    );

    List<TopPurchasedDrugDto> getTopPurchasedDrugs(
            Long clinicId,
            LocalDate from,
            LocalDate to,
            int limit
    );
}
