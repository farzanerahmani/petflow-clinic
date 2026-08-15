package com.roochi.petflowvisit.dto.response.drug;

import com.roochi.petflowvisit.dto.cmmon.DrugDto;
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
@AllArgsConstructor
@NoArgsConstructor
public class GetDrugForUpdateResponseDto {
    private DrugDto drug;
}
