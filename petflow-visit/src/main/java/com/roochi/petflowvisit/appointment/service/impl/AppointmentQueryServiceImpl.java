package com.roochi.petflowvisit.appointment.service.impl;

import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import com.roochi.petflowvisit.appointment.dto.request.AppointmentSearchRequestDto;
import com.roochi.petflowvisit.appointment.dto.response.AppointmentResponseDto;
import com.roochi.petflowvisit.appointment.entity.Appointment;
import com.roochi.petflowvisit.appointment.repository.AppointmentRepository;
import com.roochi.petflowvisit.appointment.service.AppointmentQueryService;
import com.roochi.petflowvisit.appointment.specification.AppointmentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentQueryServiceImpl
        implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentResponseDto findById(Long id) {

        Appointment appointment =
                appointmentRepository.findActiveById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        return mapToResponse(appointment);
    }
    @Override
    public Page<AppointmentResponseDto> search(
            AppointmentSearchRequestDto request,
            Pageable pageable
    ) {

        Specification<Appointment> specification =
                Specification
                        .where(AppointmentSpecification.notDeleted())
                        .and(
                                AppointmentSpecification.clinicId(
                                        request.getClinicId()
                                )
                        )
                        .and(
                                AppointmentSpecification.petId(
                                        request.getPetId()
                                )
                        )
                        .and(
                                AppointmentSpecification.doctorUserId(
                                        request.getDoctorUserId()
                                )
                        )
                        .and(
                                AppointmentSpecification.status(
                                        request.getStatus()
                                )
                        )
                        .and(
                                AppointmentSpecification.appointmentDateFrom(
                                        request.getFrom()
                                )
                        )
                        .and(
                                AppointmentSpecification.appointmentDateTo(
                                        request.getTo()
                                )
                        );

        return appointmentRepository
                .findAll(specification, pageable)
                .map(this::mapToResponse);
    }

    private AppointmentResponseDto mapToResponse(
            Appointment appointment
    ) {

        return AppointmentResponseDto.builder()
                .id(appointment.getId())
                .clinicId(appointment.getClinicId())
                .petId(appointment.getPetId())
                .doctorUserId(appointment.getDoctorUserId())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .description(appointment.getDescription())
                .status(appointment.getStatus())
                .confirmedAt(appointment.getConfirmedAt())
                .checkedInAt(appointment.getCheckedInAt())
                .cancelledAt(appointment.getCancelledAt())
                .cancellationReason(
                        appointment.getCancellationReason()
                )
                .completedAt(
                        appointment.getCompletedAt()
                )
                .build();
    }
}
