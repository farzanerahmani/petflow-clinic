package com.roochi.petflowreport.service.impl;

import com.roochi.petflowreport.dto.request.AppointmentReportRequestDto;
import com.roochi.petflowreport.dto.response.AppointmentReportRowDto;
import com.roochi.petflowreport.repository.AppointmentReportRepository;
import com.roochi.petflowreport.service.AppointmentReportService;
import com.roochi.petflowvisit.appointment.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentReportServiceImpl
        implements AppointmentReportService {

    private final AppointmentReportRepository
            appointmentReportRepository;

    @Override
    public List<AppointmentReportRowDto> getAppointmentReport(
            Long clinicId,
            AppointmentReportRequestDto request
    ) {

        LocalDateTime from = null;
        LocalDateTime to = null;

        if (request != null &&
                request.getFrom() != null) {

            from = request.getFrom()
                    .atStartOfDay();
        }

        if (request != null &&
                request.getTo() != null) {

            to = request.getTo()
                    .atTime(LocalTime.MAX);
        }

        if (from != null &&
                to != null &&
                to.isBefore(from)) {

            throw new IllegalArgumentException(
                    "'to' date cannot be before 'from' date."
            );
        }

        return appointmentReportRepository
                .getAppointmentReport(
                        clinicId,
                        from,
                        to,
                        request != null
                                ? request.getDoctorUserId()
                                : null,
                        request != null
                                ? request.getPetId()
                                : null,
                        request != null
                                ? request.getStatus()
                                : null
                )
                .stream()
                .map(this::toDto)
                .toList();
    }

    private AppointmentReportRowDto toDto(
            Appointment appointment
    ) {

        return AppointmentReportRowDto.builder()

                .appointmentId(
                        appointment.getId()
                )

                .petId(
                        appointment.getPetId()
                )

                .doctorUserId(
                        appointment.getDoctorUserId()
                )

                .appointmentDate(
                        appointment.getAppointmentDate()
                )

                .reason(
                        appointment.getReason()
                )

                .status(
                        appointment.getStatus()
                )

                .description(
                        appointment.getDescription()
                )

                .confirmedAt(
                        appointment.getConfirmedAt()
                )

                .checkedInAt(
                        appointment.getCheckedInAt()
                )

                .cancelledAt(
                        appointment.getCancelledAt()
                )

                .cancellationReason(
                        appointment.getCancellationReason()
                )

                .completedAt(
                        appointment.getCompletedAt()
                )

                .build();
    }
}
