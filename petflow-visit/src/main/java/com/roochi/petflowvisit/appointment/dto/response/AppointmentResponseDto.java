package com.roochi.petflowvisit.appointment.dto.response;

import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Builder
public class AppointmentResponseDto {

    private Long id;

    private Long clinicId;

    private Long petId;

    private Long doctorUserId;

    private LocalDateTime appointmentDate;

    private String reason;

    private String description;

    private AppointmentStatus status;

    private LocalDateTime confirmedAt;

    private LocalDateTime checkedInAt;

    private LocalDateTime cancelledAt;

    private String cancellationReason;

    private LocalDateTime completedAt;
}
