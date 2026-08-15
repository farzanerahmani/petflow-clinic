package com.roochi.petflowvisit.dto.request.procedure;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Data
public class GetVisitProcedureByIdRequestDto {

    @NotNull
    private Long id;
}
