package com.roochi.petflowreport.controller;

import com.roochi.petflowreport.dto.request.ReportDateRangeRequestDto;
import com.roochi.petflowreport.dto.response.ReportSummaryDto;
import com.roochi.petflowreport.dto.response.SaleReportRowDto;
import com.roochi.petflowreport.service.ReportExportService;
import com.roochi.petflowreport.service.SaleReportService;
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
@RequestMapping("/api/reports/sales")
@RequiredArgsConstructor
public class SaleReportController {

    private final SaleReportService saleReportService;

    private final ReportExportService reportExportService;

    @GetMapping
    public ResponseEntity<List<SaleReportRowDto>> getSalesReport(
            @ModelAttribute ReportDateRangeRequestDto request
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId = authentication.getClinicId();

        return ResponseEntity.ok(
                saleReportService.getSalesReport(
                        clinicId,
                        request
                )
        );
    }

    @GetMapping("/summary")
    public ResponseEntity<ReportSummaryDto> getSummary(
            @ModelAttribute ReportDateRangeRequestDto request
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId = authentication.getClinicId();

        return ResponseEntity.ok(
                saleReportService.getSummary(
                        clinicId,
                        request
                )
        );
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel(
            @ModelAttribute ReportDateRangeRequestDto request
    ) {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        Long clinicId =
                authentication.getClinicId();

        List<SaleReportRowDto> report =
                saleReportService.getSalesReport(
                        clinicId,
                        request
                );

        byte[] file =
                reportExportService.exportToExcel(
                        "Sales",
                        report
                );

        return ResponseEntity.ok()
                .header(
                        "Content-Disposition",
                        "attachment; filename=sales-report.xlsx"
                )
                .header(
                        "Content-Type",
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                )
                .body(file);
    }
}
