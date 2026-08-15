package com.roochi.petflowvisit.dto.response.drug;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDrugResponseDto {
    private Long drugId;
}
