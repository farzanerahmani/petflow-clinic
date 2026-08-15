package com.roochi.petflowvisit.dto.request.procedure;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class GetProcedureByIdRequestDto {

    @NotNull
    private Long id;
}
