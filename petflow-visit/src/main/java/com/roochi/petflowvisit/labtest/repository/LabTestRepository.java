package com.roochi.petflowvisit.labtest.repository;

import com.roochi.petflowvisit.drug.entity.Drug;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
public interface LabTestRepository extends JpaRepository<LabTest, Long>,
        JpaSpecificationExecutor<LabTest> {

    Boolean existsByCode(String code);

    @Query(value = "select l from LabTest l where l.id =:id and l.deleted=false ")
    Optional<LabTest> findById(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select l from LabTest l where l.id =:id and l.deleted=false ")
    Optional<LabTest> findByIdForUpdate(@Param("id") Long id);
}
