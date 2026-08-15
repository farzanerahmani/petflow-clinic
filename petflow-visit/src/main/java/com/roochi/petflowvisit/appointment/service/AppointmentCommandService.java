package com.roochi.petflowvisit.appointment.service;

import com.roochi.petflowvisit.appointment.dto.request.*;
import com.roochi.petflowvisit.appointment.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface AppointmentCommandService {

    CreateAppointmentResponseDto create(
            CreateAppointmentRequestDto request
    );

    UpdateAppointmentResponseDto update(
            UpdateAppointmentRequestDto request
    );

    void confirm(
            ConfirmAppointmentRequestDto request
    );

    void checkIn(
            CheckInAppointmentRequestDto request
    );

    void cancel(
            CancelAppointmentRequestDto request
    );

    void complete(
            Long appointmentId
    );
}
