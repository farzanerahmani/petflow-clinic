package com.roochi.petflowvisit.labresult.repository;

import com.roochi.petflowvisit.labresult.entity.LabResult;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Repository
public interface LabResultRepository extends JpaRepository<LabResult, Long> {

    @Query("""
            select lr
            from LabResult lr
            where lr.id = :id
              and lr.deleted = false
            """)
    Optional<LabResult> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select lr
            from LabResult lr
            where lr.id = :id
              and lr.deleted = false
            """)
    Optional<LabResult> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select lr
            from LabResult lr
            where lr.labRequest.id = :labRequestId
              and lr.deleted = false
            """)
    Optional<LabResult> findByLabRequestId(@Param("labRequestId") Long labRequestId);

}
