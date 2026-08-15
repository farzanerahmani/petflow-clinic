package com.roochi.petflowdashboard.service;

import com.roochi.petflowdashboard.dto.response.VisitDashboardDto;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface VisitDashboardQueryService {

    VisitDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    );
}