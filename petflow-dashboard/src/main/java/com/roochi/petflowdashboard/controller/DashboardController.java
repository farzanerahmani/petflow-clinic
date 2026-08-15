package com.roochi.petflowdashboard.controller;

import com.roochi.petflowdashboard.dto.request.DashboardRequestDto;
import com.roochi.petflowdashboard.dto.response.*;
import com.roochi.petflowdashboard.service.DashboardQueryService;
import com.roochi.petflowdashboard.service.InventoryDashboardQueryService;
import com.roochi.petflowdashboard.service.PurchaseDashboardQueryService;
import com.roochi.petflowdashboard.service.SaleDashboardQueryService;
import com.roochi.petflowshared.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardQueryService dashboardQueryService;

    private final SaleDashboardQueryService
            saleDashboardQueryService;

    private final PurchaseDashboardQueryService
            purchaseDashboardQueryService;

    private final InventoryDashboardQueryService
            inventoryDashboardQueryService;


    // =========================================================
    // MAIN DASHBOARD
    // =========================================================

    @GetMapping
    public ResponseEntity<DashboardResponseDto> getDashboard(
            @ModelAttribute DashboardRequestDto request
    ) {

        Long clinicId = getClinicId();

        return ResponseEntity.ok(
                dashboardQueryService.getDashboard(
                        clinicId,
                        request
                )
        );
    }


    // =========================================================
    // SALE REPORTS
    // =========================================================

    @GetMapping("/sales/daily")
    public ResponseEntity<List<DailySaleReportDto>>
    getDailySales(
            @ModelAttribute DashboardRequestDto request
    ) {

        LocalDate from = getFrom(request);
        LocalDate to = getTo(request, from);

        validateDates(from, to);

        return ResponseEntity.ok(
                saleDashboardQueryService.getDailyReport(
                        getClinicId(),
                        from,
                        to
                )
        );
    }


    @GetMapping("/sales/top-drugs")
    public ResponseEntity<List<TopSellingDrugDto>>
    getTopSellingDrugs(
            @ModelAttribute DashboardRequestDto request,
            @RequestParam(defaultValue = "10") int limit
    ) {

        if (limit <= 0) {
            throw new IllegalArgumentException(
                    "Limit must be greater than zero."
            );
        }

        LocalDate from = getFrom(request);
        LocalDate to = getTo(request, from);

        validateDates(from, to);

        return ResponseEntity.ok(
                saleDashboardQueryService
                        .getTopSellingDrugs(
                                getClinicId(),
                                from,
                                to,
                                limit
                        )
        );
    }


    // =========================================================
    // PURCHASE REPORTS
    // =========================================================

    @GetMapping("/purchases/daily")
    public ResponseEntity<List<DailyPurchaseReportDto>>
    getDailyPurchases(
            @ModelAttribute DashboardRequestDto request
    ) {

        LocalDate from = getFrom(request);
        LocalDate to = getTo(request, from);

        validateDates(from, to);

        return ResponseEntity.ok(
                purchaseDashboardQueryService
                        .getDailyReport(
                                getClinicId(),
                                from,
                                to
                        )
        );
    }


    @GetMapping("/purchases/top-drugs")
    public ResponseEntity<List<TopPurchasedDrugDto>>
    getTopPurchasedDrugs(
            @ModelAttribute DashboardRequestDto request,
            @RequestParam(defaultValue = "10") int limit
    ) {

        if (limit <= 0) {
            throw new IllegalArgumentException(
                    "Limit must be greater than zero."
            );
        }

        LocalDate from = getFrom(request);
        LocalDate to = getTo(request, from);

        validateDates(from, to);

        return ResponseEntity.ok(
                purchaseDashboardQueryService
                        .getTopPurchasedDrugs(
                                getClinicId(),
                                from,
                                to,
                                limit
                        )
        );
    }


    // =========================================================
    // INVENTORY REPORTS
    // =========================================================

    @GetMapping("/inventory/low-stock")
    public ResponseEntity<List<LowStockDto>>
    getLowStock() {

        return ResponseEntity.ok(
                inventoryDashboardQueryService
                        .getLowStock(getClinicId())
        );
    }


    @GetMapping("/inventory/out-of-stock")
    public ResponseEntity<List<OutOfStockDto>>
    getOutOfStock() {

        return ResponseEntity.ok(
                inventoryDashboardQueryService
                        .getOutOfStock(getClinicId())
        );
    }


    @GetMapping("/inventory/expiring-soon")
    public ResponseEntity<List<ExpiringStockDto>>
    getExpiringSoon(
            @RequestParam(defaultValue = "30") int days
    ) {

        if (days < 0) {
            throw new IllegalArgumentException(
                    "Days cannot be negative."
            );
        }

        return ResponseEntity.ok(
                inventoryDashboardQueryService
                        .getExpiringSoon(
                                getClinicId(),
                                days
                        )
        );
    }


    // =========================================================
    // PRIVATE METHODS
    // =========================================================

    private Long getClinicId() {

        JwtAuthentication authentication =
                (JwtAuthentication)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication();

        return authentication.getClinicId();
    }


    private LocalDate getFrom(
            DashboardRequestDto request
    ) {

        if (request == null ||
                request.getFrom() == null) {

            return LocalDate.now();
        }

        return request.getFrom();
    }


    private LocalDate getTo(
            DashboardRequestDto request,
            LocalDate from
    ) {

        if (request == null ||
                request.getTo() == null) {

            return from;
        }

        return request.getTo();
    }


    private void validateDates(
            LocalDate from,
            LocalDate to
    ) {

        if (to.isBefore(from)) {

            throw new IllegalArgumentException(
                    "'to' date cannot be before 'from' date."
            );
        }
    }
}
