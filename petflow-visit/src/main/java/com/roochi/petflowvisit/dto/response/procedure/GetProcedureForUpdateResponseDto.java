package com.roochi.petflowvisit.dto.response.procedure;

import com.roochi.petflowvisit.dto.cmmon.ProcedureDto;
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
public class GetProcedureForUpdateResponseDto {
    private ProcedureDto procedure;
}
