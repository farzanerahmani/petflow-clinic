package com.roochi.petflowvisit.hospitalization.repository;

import com.roochi.petflowvisit.hospitalization.entity.Hospitalization;
import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */


@Repository
public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {

    @Query("""
            select h
            from Hospitalization h
            where h.id = :id
              and h.deleted = false
            """)
    Optional<Hospitalization> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select h
            from Hospitalization h
            where h.id = :id
              and h.deleted = false
            """)
    Optional<Hospitalization> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select h
            from Hospitalization h
            where h.visit.id = :visitId
              and h.deleted = false
            order by h.admissionDate desc
            """)
    List<Hospitalization> findByVisitId(@Param("visitId") Long visitId);

    @Query("""
            select h
            from Hospitalization h
            where h.deleted = false
              and (:visitId is null or h.visit.id = :visitId)
              and (:status is null or h.status = :status)
              and (:vetId is null or h.attendingVeterinarian.id = :vetId)
              and (:fromDate is null or h.admissionDate >= :fromDate)
              and (:toDate is null or h.admissionDate <= :toDate)
            order by h.admissionDate desc
            """)
    Page<Hospitalization> search(
            @Param("visitId") Long visitId,
            @Param("status") HospitalizationStatus status,
            @Param("vetId") Long vetId,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable);

}
