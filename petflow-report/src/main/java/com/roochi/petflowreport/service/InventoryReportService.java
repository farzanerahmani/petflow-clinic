package com.roochi.petflowreport.service;

import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.InventoryReportRowDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */
public interface InventoryReportService {

    List<InventoryReportRowDto> getInventoryReport(
            Long clinicId,
            ReportDateRangeRequestDto request
    );
}
