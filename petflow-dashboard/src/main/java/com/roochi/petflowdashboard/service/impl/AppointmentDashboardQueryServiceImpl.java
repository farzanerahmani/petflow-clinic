package com.roochi.petflowdashboard.service.impl;

import com.roochi.petflowdashboard.dto.response.AppointmentDashboardDto;
import com.roochi.petflowdashboard.repository.AppointmentDashboardRepository;
import com.roochi.petflowdashboard.service.AppointmentDashboardQueryService;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;
/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentDashboardQueryServiceImpl
        implements AppointmentDashboardQueryService {

    private final AppointmentDashboardRepository
            appointmentDashboardRepository;

    @Override
    public AppointmentDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    ) {

        LocalDateTime fromDateTime =
                from.atStartOfDay();

        LocalDateTime toDateTime =
                to.plusDays(1).atStartOfDay();

        Map<AppointmentStatus, Long> counts =
                new EnumMap<>(AppointmentStatus.class);

        for (AppointmentStatus status :
                AppointmentStatus.values()) {

            counts.put(status, 0L);
        }

        appointmentDashboardRepository
                .countByStatus(
                        clinicId,
                        fromDateTime,
                        toDateTime
                )
                .forEach(row -> {

                    AppointmentStatus status =
                            (AppointmentStatus) row[0];

                    Long count =
                            ((Number) row[1]).longValue();

                    counts.put(status, count);
                });

        long scheduled =
                counts.getOrDefault(
                        AppointmentStatus.SCHEDULED,
                        0L
                );

        long confirmed =
                counts.getOrDefault(
                        AppointmentStatus.CONFIRMED,
                        0L
                );

        long checkedIn =
                counts.getOrDefault(
                        AppointmentStatus.CHECKED_IN,
                        0L
                );

        long completed =
                counts.getOrDefault(
                        AppointmentStatus.COMPLETED,
                        0L
                );

        long cancelled =
                counts.getOrDefault(
                        AppointmentStatus.CANCELLED,
                        0L
                );

        long noShow =
                counts.getOrDefault(
                        AppointmentStatus.NO_SHOW,
                        0L
                );

        long total =
                scheduled
                        + confirmed
                        + checkedIn
                        + completed
                        + cancelled
                        + noShow;

        return AppointmentDashboardDto.builder()
                .scheduled(scheduled)
                .confirmed(confirmed)
                .checkedIn(checkedIn)
                .completed(completed)
                .cancelled(cancelled)
                .noShow(noShow)
                .total(total)
                .build();
    }
}
