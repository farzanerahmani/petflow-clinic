package com.roochi.petflowvisit.dto.request.labrequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Data
public class DeleteLabRequestRequestDto {

    @NotNull
    private Long id;
}
