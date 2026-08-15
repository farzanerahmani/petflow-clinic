package com.roochi.petflowvisit.dto.response.drug;

import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDrugByIdResponseDto {

    private DrugDto drug;
}
