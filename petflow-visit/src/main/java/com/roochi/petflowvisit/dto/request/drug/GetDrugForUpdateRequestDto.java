package com.roochi.petflowvisit.dto.request.drug;

import lombok.Data;
import lombok.NonNull;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class GetDrugForUpdateRequestDto {
    @NonNull
    private Long drugId;
}
