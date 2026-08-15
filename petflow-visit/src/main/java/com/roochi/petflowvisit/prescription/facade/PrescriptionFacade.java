package com.roochi.petflowvisit.prescription.facade;

import com.roochi.petflowvisit.prescription.entity.Prescription;

/**
 * @author farzane.rahmani
 * @created 7/14/2026
 */
public interface PrescriptionFacade {

    Prescription getPrescriptionForUpdate(Long visitId);
}
