package com.roochi.petflowvisit.dto.request.prescription;

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
public class DeletePrescriptionRequestDto {

    @NonNull
    private Long visitId;
}
