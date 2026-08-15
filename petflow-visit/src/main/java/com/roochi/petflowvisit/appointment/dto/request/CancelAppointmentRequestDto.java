package com.roochi.petflowvisit.appointment.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class CancelAppointmentRequestDto {

    @NotNull
    private Long appointmentId;

    @Size(max = 500)
    private String reason;
}
