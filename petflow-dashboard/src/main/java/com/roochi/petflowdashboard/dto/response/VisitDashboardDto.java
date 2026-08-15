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
public class VisitDashboardDto {

    private long total;

    private long inProgress;

    private long completed;
}
