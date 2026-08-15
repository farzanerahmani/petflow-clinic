package com.roochi.petflowreport.dto.request;

import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentReportRequestDto {

    private LocalDate from;

    private LocalDate to;

    private Long doctorUserId;

    private Long petId;

    private AppointmentStatus status;
}
