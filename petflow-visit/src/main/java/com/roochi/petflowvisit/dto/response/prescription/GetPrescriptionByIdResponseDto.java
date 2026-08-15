package com.roochi.petflowvisit.dto.response.prescription;

import com.roochi.petflowvisit.dto.cmmon.PrescriptionDto;
import com.roochi.petflowvisit.dto.cmmon.PrescriptionItemDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/13/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPrescriptionByIdResponseDto {

    private PrescriptionDto prescription;
}
