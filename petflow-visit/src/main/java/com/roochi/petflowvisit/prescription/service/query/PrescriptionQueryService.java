package com.roochi.petflowvisit.prescription.service.query;

import com.roochi.petflowvisit.dto.request.prescription.GetPrescriptionByIdRequestDto;
import com.roochi.petflowvisit.dto.response.prescription.GetPrescriptionByIdResponseDto;
import com.roochi.petflowvisit.prescription.entity.Prescription;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
public interface PrescriptionQueryService {

    GetPrescriptionByIdResponseDto getPrescriptionByVisitId(GetPrescriptionByIdRequestDto requestDto);

    Prescription getPrescriptionByVisitIdForUpdate(Long visitId);

}
