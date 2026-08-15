package com.roochi.petflowvisit.medicalrecord.service.query;

import com.roochi.petflowvisit.dto.request.medicalrecord.GetMedicalRecordByVisitIdRequestDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.GetMedicalRecordByVisitIdResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public interface MedicalRecordQueryService {

    GetMedicalRecordByVisitIdResponseDto getMedicalRecordByVisitId(GetMedicalRecordByVisitIdRequestDto requestDto);
}
