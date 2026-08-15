package com.roochi.petflowvisit.medicalrecord.facade;

import com.roochi.petflowvisit.dto.cmmon.MedicalRecordDto;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public interface MedicalRecordFacade {

    MedicalRecordDto getMedicalRecord(Long visitId);

    void validateMedicalRecord(Long visitId);

    boolean exists(Long visitId);
}
