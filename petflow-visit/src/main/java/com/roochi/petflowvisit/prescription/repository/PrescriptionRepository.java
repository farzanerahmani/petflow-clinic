package com.roochi.petflowvisit.prescription.repository;

import com.roochi.petflowvisit.prescription.entity.Prescription;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>, JpaSpecificationExecutor<Prescription> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select p from Prescription p where p.visit.id =:visitId " +
            "and p.deleted=false ")
    Optional<Prescription> findByVisitIdForUpdate(@Param("visitId") Long visitId);

    Optional<Prescription> findByVisitIdAndDeletedFalse(Long visitId);
}
