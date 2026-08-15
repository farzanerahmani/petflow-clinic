package com.roochi.petflowreport.service;

import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.ReportSummaryDto;
import com.roochi.petflowreport.dto.response.SaleReportRowDto;

import java.util.List;

public interface SaleReportService {

    List<SaleReportRowDto> getSalesReport(
            Long clinicId,
            ReportDateRangeRequestDto request
    );

    ReportSummaryDto getSummary(
            Long clinicId,
            ReportDateRangeRequestDto request
    );
}