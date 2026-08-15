package com.roochi.petflowreport.service.impl;

import com.roochi.petflowreport.dto.request.VisitReportRequestDto;
import com.roochi.petflowreport.dto.response.VisitReportRowDto;
import com.roochi.petflowreport.repository.VisitReportRepository;
import com.roochi.petflowreport.service.VisitReportService;
import com.roochi.petflowvisit.visit.entity.Visit;
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
public class VisitReportServiceImpl
        implements VisitReportService {

    private final VisitReportRepository visitReportRepository;

    @Override
    public List<VisitReportRowDto> getVisitReport(
            Long clinicId,
            VisitReportRequestDto request
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

        return visitReportRepository
                .getVisitReport(
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
                                : null,
                        request != null
                                ? request.getType()
                                : null
                )
                .stream()
                .map(this::toDto)
                .toList();
    }

    private VisitReportRowDto toDto(Visit visit) {

        return VisitReportRowDto.builder()

                .visitId(
                        visit.getId()
                )

                .petId(
                        visit.getPetId()
                )

                .doctorUserId(
                        visit.getDoctorUserId()
                )

                .visitDate(
                        visit.getVisitDate()
                )

                .status(
                        visit.getStatus()
                )

                .type(
                        visit.getType()
                )

                .chiefComplaint(
                        visit.getChiefComplaint()
                )

                .diagnosis(
                        visit.getDiagnosis()
                )

                .weight(
                        visit.getWeight()
                )

                .temperature(
                        visit.getTemperature()
                )

                .heartRate(
                        visit.getHeartRate()
                )

                .respiratoryRate(
                        visit.getRespiratoryRate()
                )

                .finishedAt(
                        visit.getFinishedAt()
                )

                .build();
    }
}
