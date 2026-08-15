package com.roochi.petflowreport.service;

import com.roochi.petflowreport.dto.request.VisitReportRequestDto;
import com.roochi.petflowreport.dto.response.VisitReportRowDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


public interface VisitReportService {

    List<VisitReportRowDto> getVisitReport(
            Long clinicId,
            VisitReportRequestDto request
    );
}
