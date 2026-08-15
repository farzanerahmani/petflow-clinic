package com.roochi.petflowreport.service;

import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.PurchaseReportRowDto;
import com.roochi.petflowreport.dto.response.ReportSummaryDto;

import java.util.List;

public interface PurchaseReportService {

    List<PurchaseReportRowDto> getPurchaseReport(
            Long clinicId,
            ReportDateRangeRequestDto request
    );

    ReportSummaryDto getSummary(
            Long clinicId,
            ReportDateRangeRequestDto request
    );
}