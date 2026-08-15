package com.roochi.petflowvisit.appointment.service.impl;

import com.roochi.petflowaudit.aop.Auditable;
import com.roochi.petflowaudit.entity.enums.AuditAction;
import com.roochi.petflowaudit.entity.enums.AuditEntityType;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.appointment.dto.request.*;
import com.roochi.petflowvisit.appointment.dto.response.CreateAppointmentResponseDto;
import com.roochi.petflowvisit.appointment.dto.response.UpdateAppointmentResponseDto;
import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import com.roochi.petflowvisit.appointment.repository.AppointmentRepository;
import com.roochi.petflowvisit.appointment.service.AppointmentCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentCommandServiceImpl
        implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;


    @Override
    @Auditable(
            action = AuditAction.CREATE,
            entityType = AuditEntityType.APPOINTMENT,
            description = "Appointment created"
    )
    public CreateAppointmentResponseDto create(
            CreateAppointmentRequestDto request
    ) {

        validateDoctorAvailability(
                request.getDoctorUserId(),
                request.getAppointmentDate(),
                null
        );

        Appointment appointment =
                Appointment.builder()
                        .clinicId(request.getClinicId())
                        .petId(request.getPetId())
                        .doctorUserId(request.getDoctorUserId())
                        .appointmentDate(request.getAppointmentDate())
                        .reason(request.getReason())
                        .description(request.getDescription())
                        .status(AppointmentStatus.SCHEDULED)
                        .build();

        appointmentRepository.save(appointment);

        return CreateAppointmentResponseDto.builder()
                .id(appointment.getId())
                .build();
    }

    @Auditable(
            action = AuditAction.UPDATE,
            entityType = AuditEntityType.APPOINTMENT,
            entityIdParam = "appointmentId",
            description = "Appointment updated"
    )
    @Override
    public UpdateAppointmentResponseDto update(
            UpdateAppointmentRequestDto request
    ) {

        Appointment appointment =
                appointmentRepository.findByIdForUpdate(
                                request.getId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (appointment.getStatus() != AppointmentStatus.SCHEDULED
                && appointment.getStatus() != AppointmentStatus.CONFIRMED) {

            throw new IllegalStateException(
                    "Only scheduled or confirmed appointments can be updated."
            );
        }

        validateDoctorAvailability(
                request.getDoctorUserId(),
                request.getAppointmentDate(),
                appointment.getId()
        );

        appointment.setDoctorUserId(
                request.getDoctorUserId()
        );

        appointment.setAppointmentDate(
                request.getAppointmentDate()
        );

        appointment.setReason(
                request.getReason()
        );

        appointment.setDescription(
                request.getDescription()
        );

        return UpdateAppointmentResponseDto.builder()
                .id(appointment.getId())
                .build();
    }


    @Override
    public void confirm(
            ConfirmAppointmentRequestDto request
    ) {

        Appointment appointment =
                getForUpdate(
                        request.getAppointmentId()
                );

        requireStatus(
                appointment,
                AppointmentStatus.SCHEDULED
        );

        appointment.setStatus(
                AppointmentStatus.CONFIRMED
        );

        appointment.setConfirmedAt(
                java.time.LocalDateTime.now()
        );
    }


    @Override
    public void checkIn(
            CheckInAppointmentRequestDto request
    ) {

        Appointment appointment =
                getForUpdate(
                        request.getAppointmentId()
                );

        requireStatus(
                appointment,
                AppointmentStatus.CONFIRMED
        );

        appointment.setStatus(
                AppointmentStatus.CHECKED_IN
        );

        appointment.setCheckedInAt(
                java.time.LocalDateTime.now()
        );
    }


    @Override
    public void cancel(
            CancelAppointmentRequestDto request
    ) {

        Appointment appointment =
                getForUpdate(
                        request.getAppointmentId()
                );

        if (appointment.getStatus() == AppointmentStatus.COMPLETED
                || appointment.getStatus() == AppointmentStatus.CANCELLED
                || appointment.getStatus() == AppointmentStatus.NO_SHOW) {

            throw new IllegalStateException(
                    "Appointment cannot be cancelled in current status."
            );
        }

        appointment.setStatus(
                AppointmentStatus.CANCELLED
        );

        appointment.setCancelledAt(
                java.time.LocalDateTime.now()
        );

        appointment.setCancellationReason(
                request.getReason()
        );
    }

    @Override
    public void complete(Long appointmentId) {

        Appointment appointment =
                getForUpdate(appointmentId);

        requireStatus(
                appointment,
                AppointmentStatus.CHECKED_IN
        );

        appointment.setStatus(
                AppointmentStatus.COMPLETED
        );

        appointment.setCompletedAt(
                java.time.LocalDateTime.now()
        );
    }

    private Appointment getForUpdate(
            Long appointmentId
    ) {

        return appointmentRepository
                .findByIdForUpdate(appointmentId)
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorCode.INTERNAL_ERROR
                        )
                );
    }


    private void requireStatus(
            Appointment appointment,
            AppointmentStatus expectedStatus
    ) {

        if (appointment.getStatus() != expectedStatus) {

            throw new IllegalStateException(
                    "Invalid appointment status transition. " +
                            "Current status: " +
                            appointment.getStatus() +
                            ", expected: " +
                            expectedStatus
            );
        }
    }


    private void validateDoctorAvailability(
            Long doctorUserId,
            LocalDateTime appointmentDate,
            Long currentAppointmentId
    ) {

        boolean conflict =
                appointmentRepository.existsDoctorConflict(
                        doctorUserId,
                        appointmentDate,
                        Set.of(
                                AppointmentStatus.SCHEDULED,
                                AppointmentStatus.CONFIRMED,
                                AppointmentStatus.CHECKED_IN
                        ),
                        currentAppointmentId
                );

        if (conflict) {

            throw new IllegalStateException(
                    "Doctor already has an appointment at this time."
            );
        }
    }
}
