package com.roochi.petflowreport.controller;

import com.roochi.petflowreport.dto.request.VisitReportRequestDto;
import com.roochi.petflowreport.dto.response.VisitReportRowDto;
import com.roochi.petflowreport.service.VisitReportService;
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
@RequestMapping("/api/reports/visits")
@RequiredArgsConstructor
public class VisitReportController {

    private final VisitReportService visitReportService;

    @GetMapping
    public ResponseEntity<List<VisitReportRowDto>>
    getVisitReport(
            @ModelAttribute VisitReportRequestDto request
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        return ResponseEntity.ok(
                visitReportService.getVisitReport(
                        clinicId,
                        request
                )
        );
    }
}
