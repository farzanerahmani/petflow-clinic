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
public class AppointmentDashboardDto {

    private long scheduled;

    private long confirmed;

    private long checkedIn;

    private long completed;

    private long cancelled;

    private long noShow;

    private long total;
}