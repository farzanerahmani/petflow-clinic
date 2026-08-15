package com.roochi.petflowvisit.labresult.repository;

import com.roochi.petflowvisit.labresult.entity.LabTestParameter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Repository
public interface LabTestParameterRepository extends JpaRepository<LabTestParameter, Long>,
        JpaSpecificationExecutor<LabTestParameter> {

    @Query("""
            select p
            from LabTestParameter p
            where p.id = :id
              and p.deleted = false
            """)
    Optional<LabTestParameter> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select p
            from LabTestParameter p
            where p.id = :id
              and p.deleted = false
            """)
    Optional<LabTestParameter> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select p
            from LabTestParameter p
            where p.labTest.id = :labTestId
              and p.deleted = false
              and p.active = true
            order by p.name
            """)
    List<LabTestParameter> findByLabTestId(@Param("labTestId") Long labTestId);
}
