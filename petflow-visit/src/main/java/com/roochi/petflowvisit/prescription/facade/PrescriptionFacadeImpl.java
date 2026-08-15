package com.roochi.petflowvisit.prescription.facade;

import com.roochi.petflowvisit.prescription.entity.Prescription;
import com.roochi.petflowvisit.prescription.service.query.PrescriptionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/14/2026
 */
@Component
@RequiredArgsConstructor
public class PrescriptionFacadeImpl implements PrescriptionFacade{

    private final PrescriptionQueryService prescriptionQueryService;
    @Override
    public Prescription getPrescriptionForUpdate(Long visitId) {

        return prescriptionQueryService.getPrescriptionByVisitIdForUpdate(visitId);
    }
}
