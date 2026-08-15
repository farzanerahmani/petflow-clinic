package com.roochi.petflowvisit.procedure.repository;

import com.roochi.petflowvisit.procedure.entity.VisitProcedure;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */


@Repository
public interface VisitProcedureRepository extends JpaRepository<VisitProcedure, Long> {

    @Query("""
            select vp
            from VisitProcedure vp
            where vp.id = :id
              and vp.deleted = false
            """)
    Optional<VisitProcedure> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select vp
            from VisitProcedure vp
            where vp.id = :id
              and vp.deleted = false
            """)
    Optional<VisitProcedure> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select vp
            from VisitProcedure vp
            where vp.visit.id = :visitId
              and vp.deleted = false
            order by vp.performedDate desc
            """)
    List<VisitProcedure> findByVisitId(@Param("visitId") Long visitId);

    @Query("""
            select vp
            from VisitProcedure vp
            where vp.deleted = false
              and (:visitId is null or vp.visit.id = :visitId)
              and (:procedureId is null or vp.procedure.id = :procedureId)
              and (:performedById is null or vp.performedBy.id = :performedById)
              and (:fromDate is null or vp.performedDate >= :fromDate)
              and (:toDate is null or vp.performedDate <= :toDate)
            order by vp.performedDate desc
            """)
    Page<VisitProcedure> search(
            @Param("visitId") Long visitId,
            @Param("procedureId") Long procedureId,
            @Param("performedById") Long performedById,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable);
}
