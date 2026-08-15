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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDrugResponseDto {
    private Long drugId;
}
