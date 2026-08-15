package com.roochi.petflowvisit.dto.request.labrequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Data
public class GetLabRequestForUpdateRequestDto {

    @NotNull
    private Long id;
}
