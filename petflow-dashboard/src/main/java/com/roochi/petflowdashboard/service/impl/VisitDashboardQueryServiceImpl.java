package com.roochi.petflowdashboard.service.impl;

import com.roochi.petflowdashboard.dto.response.VisitDashboardDto;
import com.roochi.petflowdashboard.repository.VisitDashboardRepository;
import com.roochi.petflowdashboard.service.VisitDashboardQueryService;
import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
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
public class VisitDashboardQueryServiceImpl
        implements VisitDashboardQueryService {

    private final VisitDashboardRepository
            visitDashboardRepository;

    @Override
    public VisitDashboardDto getSummary(
            Long clinicId,
            LocalDate from,
            LocalDate to
    ) {

        LocalDateTime fromDateTime =
                from.atStartOfDay();

        LocalDateTime toDateTime =
                to.plusDays(1).atStartOfDay();

        Map<VisitStatus, Long> counts =
                new EnumMap<>(VisitStatus.class);

        for (VisitStatus status : VisitStatus.values()) {
            counts.put(status, 0L);
        }

        visitDashboardRepository
                .countByStatus(
                        clinicId,
                        fromDateTime,
                        toDateTime
                )
                .forEach(row -> {

                    VisitStatus status =
                            (VisitStatus) row[0];

                    Long count =
                            ((Number) row[1]).longValue();

                    counts.put(status, count);
                });

        long total = counts.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();

        long inProgress =
                counts.getOrDefault(
                        VisitStatus.IN_PROGRESS,
                        0L
                );

        long completed =
                counts.getOrDefault(
                        VisitStatus.COMPLETED,
                        0L
                );

        return VisitDashboardDto.builder()
                .total(total)
                .inProgress(inProgress)
                .completed(completed)
                .build();
    }
}
