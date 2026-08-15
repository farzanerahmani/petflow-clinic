package com.roochi.petflowdashboard.service;

import com.roochi.petflowdashboard.dto.response.AppointmentDashboardDto;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface AppointmentDashboardQueryService {

    AppointmentDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    );
}
