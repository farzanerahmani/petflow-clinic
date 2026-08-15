package com.roochi.petflowvisit.appointment.dto.request;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Getter
@Setter
public class AppointmentSearchRequestDto {

    private Long clinicId;

    private Long petId;

    private Long doctorUserId;

    private AppointmentStatus status;

    private LocalDateTime from;

    private LocalDateTime to;
}
