package com.roochi.petflowvisit.hospitalization.repository;

import com.roochi.petflowvisit.hospitalization.entity.HospitalizationDailyNote;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Repository
public interface HospitalizationDailyNoteRepository
        extends JpaRepository<HospitalizationDailyNote, Long> {

    @Query("""
            select hdn
            from HospitalizationDailyNote hdn
            where hdn.id = :id
              and hdn.deleted = false
            """)
    Optional<HospitalizationDailyNote> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select hdn
            from HospitalizationDailyNote hdn
            where hdn.id = :id
              and hdn.deleted = false
            """)
    Optional<HospitalizationDailyNote> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select hdn
            from HospitalizationDailyNote hdn
            where hdn.hospitalization.id = :hospitalizationId
              and hdn.deleted = false
            order by hdn.recordDateTime desc
            """)
    List<HospitalizationDailyNote> findByHospitalizationId(
            @Param("hospitalizationId") Long hospitalizationId);

    @Query("""
            select hdn
            from HospitalizationDailyNote hdn
            where hdn.deleted = false
              and (:hospitalizationId is null or hdn.hospitalization.id = :hospitalizationId)
              and (:veterinarianId is null or hdn.veterinarian.id = :veterinarianId)
              and (:fromDate is null or hdn.recordDateTime >= :fromDate)
              and (:toDate is null or hdn.recordDateTime <= :toDate)
            order by hdn.recordDateTime desc
            """)
    Page<HospitalizationDailyNote> search(
            @Param("hospitalizationId") Long hospitalizationId,
            @Param("veterinarianId") Long veterinarianId,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate
            , Pageable pageable);

}
