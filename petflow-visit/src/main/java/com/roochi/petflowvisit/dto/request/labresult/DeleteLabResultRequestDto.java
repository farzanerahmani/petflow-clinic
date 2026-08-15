package com.roochi.petflowvisit.dto.request.labresult;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/22/2026
 */
@Data
public class DeleteLabResultRequestDto {

    @NotNull
    private Long id;
}


