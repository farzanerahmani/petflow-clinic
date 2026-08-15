package com.roochi.petflowvisit.medicalrecord.repository;

import com.roochi.petflowvisit.medicalrecord.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>,
        JpaSpecificationExecutor<MedicalRecord> {

    Optional<MedicalRecord> findByVisitId(Long visitId);

    Optional<MedicalRecord> findByIdAndClinicId(Long id , Long clinicId);

    boolean existsByVisitId(Long visitId);
}
