package com.roochi.petflowvisit.dto.request.labtest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class GetLabTestByIdRequestDto {

    @NotNull
    private Long id;
}
