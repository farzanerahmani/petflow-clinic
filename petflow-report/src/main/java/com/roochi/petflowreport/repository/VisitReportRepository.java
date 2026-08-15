package com.roochi.petflowreport.repository;

import com.roochi.petflowvisit.visit.entity.Visit;
import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 8/9/2026
 */


public interface VisitReportRepository
        extends JpaRepository<Visit, Long> {

    @Query("""
        select v
        from Visit v
        where v.clinicId = :clinicId
          and (:from is null or v.visitDate >= :from)
          and (:to is null or v.visitDate <= :to)
          and (:doctorUserId is null or v.doctorUserId = :doctorUserId)
          and (:petId is null or v.petId = :petId)
          and (:status is null or v.status = :status)
          and (:type is null or v.type = :type)
        order by v.visitDate desc
        """)
    List<Visit> getVisitReport(
            @Param("clinicId") Long clinicId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("doctorUserId") Long doctorUserId,
            @Param("petId") Long petId,
            @Param("status") VisitStatus status,
            @Param("type") VisitType type
    );
}
