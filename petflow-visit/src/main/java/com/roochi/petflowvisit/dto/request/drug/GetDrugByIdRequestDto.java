package com.roochi.petflowvisit.dto.request.drug;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class GetDrugByIdRequestDto {

    @NotNull
    private Long id;
}
