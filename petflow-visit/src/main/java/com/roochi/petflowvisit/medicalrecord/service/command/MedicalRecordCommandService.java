package com.roochi.petflowvisit.medicalrecord.service.command;

import com.roochi.petflowvisit.dto.request.medicalrecord.AddMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.request.medicalrecord.UpdateMedicalRecordRequestDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.AddMedicalRecordResponseDto;
import com.roochi.petflowvisit.dto.response.medicalrecord.UpdateMedicalRecordResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
public interface MedicalRecordCommandService {

    AddMedicalRecordResponseDto addMedicalRecord(AddMedicalRecordRequestDto responseDto);

    UpdateMedicalRecordResponseDto updateMedicalRecord(UpdateMedicalRecordRequestDto requestDto);
}
