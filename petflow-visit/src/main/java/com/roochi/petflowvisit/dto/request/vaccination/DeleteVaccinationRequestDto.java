package com.roochi.petflowvisit.dto.request.vaccination;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Data
public class DeleteVaccinationRequestDto {

    @NotNull
    private Long id;
}
