package com.roochi.petflowreport.service;

import com.roochi.petflowreport.dto.request.AppointmentReportRequestDto;
import com.roochi.petflowreport.dto.response.AppointmentReportRowDto;

import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */
public interface AppointmentReportService {

    List<AppointmentReportRowDto> getAppointmentReport(
            Long clinicId,
            AppointmentReportRequestDto request
    );
}
