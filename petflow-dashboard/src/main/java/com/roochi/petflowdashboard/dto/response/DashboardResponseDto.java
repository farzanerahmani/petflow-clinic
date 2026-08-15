package com.roochi.petflowdashboard.dto.response;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDto {

    private AppointmentDashboardDto appointments;

    private VisitDashboardDto visits;

    private InventoryDashboardDto inventory;

    private SaleDashboardDto sales;

    private PurchaseDashboardDto purchases;
}
