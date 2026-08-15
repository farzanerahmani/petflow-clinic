package com.roochi.petflowreport.controller;

import com.roochi.petflowreport.dto.request.AppointmentReportRequestDto;
import com.roochi.petflowreport.dto.response.AppointmentReportRowDto;
import com.roochi.petflowreport.service.AppointmentReportService;
import com.roochi.petflowshared.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */



@RestController
@RequestMapping("/api/reports/appointments")
@RequiredArgsConstructor
public class AppointmentReportController {

    private final AppointmentReportService
            appointmentReportService;

    @GetMapping
    public ResponseEntity<List<AppointmentReportRowDto>>
    getAppointmentReport(
            @ModelAttribute AppointmentReportRequestDto request
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        return ResponseEntity.ok(
                appointmentReportService.getAppointmentReport(
                        clinicId,
                        request
                )
        );
    }
}
