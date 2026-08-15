package com.roochi.petflowvisit.labrequest.repository;

import com.roochi.petflowvisit.labrequest.entity.LabRequest;
import com.roochi.petflowvisit.vaccination.entity.Vaccination;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Repository
public interface LabRequestRepository extends JpaRepository<LabRequest, Long> {

    @Query("""
            select l
            from LabRequest l
            where l.id = :id
              and l.deleted = false
            """)
    Optional<LabRequest> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select l
            from LabRequest l
            where l.id = :id
              and l.deleted = false
            """)
    Optional<LabRequest> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select l
            from LabRequest l
            where l.visit.id = :visitId
              and l.deleted = false
            order by l.requestDate desc
            """)
    Page<LabRequest> findByVisitId(@Param("visitId") Long visitId, Pageable pageable);

}
