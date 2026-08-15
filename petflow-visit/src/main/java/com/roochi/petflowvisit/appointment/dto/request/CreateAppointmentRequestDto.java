package com.roochi.petflowvisit.appointment.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class CreateAppointmentRequestDto {

    @NotNull
    private Long clinicId;

    @NotNull
    private Long petId;

    @NotNull
    private Long doctorUserId;

    @NotNull
    @Future
    private LocalDateTime appointmentDate;

    @Size(max = 1000)
    private String reason;

    @Size(max = 1000)
    private String description;
}
