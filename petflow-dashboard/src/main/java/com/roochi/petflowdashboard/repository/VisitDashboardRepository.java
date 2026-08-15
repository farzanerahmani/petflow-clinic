package com.roochi.petflowdashboard.repository;

import com.roochi.petflowvisit.visit.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/8/2026
 */
public interface VisitDashboardRepository
        extends JpaRepository<Visit, Long> {

    @Query("""
            select v.status, count(v)
            from Visit v
            where v.clinicId = :clinicId
              and v.visitDate >= :from
              and v.visitDate < :to
            group by v.status
            """)
    List<Object[]> countByStatus(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
