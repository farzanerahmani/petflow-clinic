package com.roochi.petflowvisit.dto.response.procedure;

import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.ProcedureDto;
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
public class GetProcedureByIdResponseDto {

    private ProcedureDto procedure;
}
