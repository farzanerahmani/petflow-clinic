package com.roochi.petflowvisit.dto.response.medicalrecord;

import com.roochi.petflowvisit.dto.cmmon.MedicalRecordDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/11/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMedicalRecordByVisitIdResponseDto {
    private MedicalRecordDto medicalRecord;
}
