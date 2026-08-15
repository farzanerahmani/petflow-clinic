package com.roochi.petflowreport.dto.response;

import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentReportRowDto {

    private Long appointmentId;

    private Long petId;

    private Long doctorUserId;

    private LocalDateTime appointmentDate;

    private String reason;

    private AppointmentStatus status;

    private String description;

    private LocalDateTime confirmedAt;

    private LocalDateTime checkedInAt;

    private LocalDateTime cancelledAt;

    private String cancellationReason;

    private LocalDateTime completedAt;
}
