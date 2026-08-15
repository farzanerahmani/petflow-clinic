package com.roochi.petflowvisit.dto.request.procedure;

import lombok.Data;
import lombok.NonNull;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class GetProcedureForUpdateRequestDto {
    @NonNull
    private Long id;
}
