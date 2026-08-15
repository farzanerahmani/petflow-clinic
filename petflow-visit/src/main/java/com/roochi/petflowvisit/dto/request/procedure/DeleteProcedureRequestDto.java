package com.roochi.petflowvisit.dto.request.procedure;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Data
public class DeleteProcedureRequestDto {
    @NotNull
    private Long id;
}
