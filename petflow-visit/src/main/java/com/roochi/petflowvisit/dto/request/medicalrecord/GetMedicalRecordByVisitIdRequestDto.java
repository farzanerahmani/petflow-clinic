package com.roochi.petflowvisit.dto.request.medicalrecord;

import jakarta.validation.constraints.NotNull;
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
public class GetMedicalRecordByVisitIdRequestDto {

    @NotNull
    private Long visitId;
}
