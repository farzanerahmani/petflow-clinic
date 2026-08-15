package com.roochi.petflowdashboard.service.impl;

import com.roochi.petflowdashboard.dto.request.DashboardRequestDto;
import com.roochi.petflowdashboard.dto.response.DashboardResponseDto;
import com.roochi.petflowdashboard.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardQueryServiceImpl
        implements DashboardQueryService {

    private final AppointmentDashboardQueryService
            appointmentDashboardQueryService;

    private final VisitDashboardQueryService
            visitDashboardQueryService;

    private final InventoryDashboardQueryService
            inventoryDashboardQueryService;

    private final SaleDashboardQueryService
            saleDashboardQueryService;

    private final PurchaseDashboardQueryService
            purchaseDashboardQueryService;

    @Override
    public DashboardResponseDto getDashboard(
            Long clinicId,
            DashboardRequestDto request
    ) {

        LocalDate from = request != null
                ? request.getFrom()
                : null;

        LocalDate to = request != null
                ? request.getTo()
                : null;

        if (from == null) {
            from = LocalDate.now();
        }

        if (to == null) {
            to = from;
        }

        if (to.isBefore(from)) {
            throw new IllegalArgumentException(
                    "'to' date cannot be before 'from' date."
            );
        }

        return DashboardResponseDto.builder()

                .appointments(
                        appointmentDashboardQueryService
                                .getSummary(
                                        clinicId,
                                        from,
                                        to
                                )
                )

                .visits(
                        visitDashboardQueryService
                                .getSummary(
                                        clinicId,
                                        from,
                                        to
                                )
                )

                .inventory(
                        inventoryDashboardQueryService
                                .getSummary(
                                        clinicId
                                )
                )

                .sales(
                        saleDashboardQueryService
                                .getSummary(
                                        clinicId,
                                        from,
                                        to
                                )
                )

                .purchases(
                        purchaseDashboardQueryService
                                .getSummary(
                                        clinicId,
                                        from,
                                        to
                                )
                )

                .build();
    }
}