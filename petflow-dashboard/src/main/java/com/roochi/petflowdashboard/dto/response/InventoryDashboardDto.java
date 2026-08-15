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
public class InventoryDashboardDto {

    private long lowStock;

    private long outOfStock;

    private long activeReservations;
}
