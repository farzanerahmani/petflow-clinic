package com.roochi.petflowvisit.imaging.repository;

import com.roochi.petflowvisit.imaging.entity.ImagingRequest;
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
 * @created 7/22/2026
 */


@Repository
public interface ImagingRequestRepository extends JpaRepository<ImagingRequest, Long> {

    @Query("""
            select ir
            from ImagingRequest ir
            where ir.id = :id
              and ir.deleted = false
            """)
    Optional<ImagingRequest> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select ir
            from ImagingRequest ir
            where ir.id = :id
              and ir.deleted = false
            """)
    Optional<ImagingRequest> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select ir
            from ImagingRequest ir
            where ir.visit.id = :visitId
              and ir.deleted = false
            order by ir.requestDate desc
            """)
    List<ImagingRequest> findByVisitId(@Param("visitId") Long visitId);

    @Query("""
            select ir
            from ImagingRequest ir
            where ir.deleted = false
              and (:visitId is null or ir.visit.id = :visitId)
              and (:serviceId is null or ir.imagingService.id = :serviceId)
              and (:fromDate is null or ir.requestDate >= :fromDate)
              and (:toDate is null or ir.requestDate <= :toDate)
            order by ir.requestDate desc
            """)
    Page<ImagingRequest> search(
            @Param("visitId") Long visitId,
            @Param("serviceId") Long serviceId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable);
}
