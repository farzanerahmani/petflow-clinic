package com.roochi.petflowreport.controller;

import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.InventoryReportRowDto;
import com.roochi.petflowreport.service.InventoryReportService;
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
@RequestMapping("/api/reports/inventory")
@RequiredArgsConstructor
public class InventoryReportController {

    private final InventoryReportService inventoryReportService;

    @GetMapping
    public ResponseEntity<List<InventoryReportRowDto>>
    getInventoryReport(
            @ModelAttribute ReportDateRangeRequestDto request
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        return ResponseEntity.ok(
                inventoryReportService.getInventoryReport(
                        clinicId,
                        request
                )
        );
    }
}
