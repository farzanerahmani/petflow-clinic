package com.roochi.petflowvisit.dto.request.hospitalization;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Data
public class DeleteHospitalizationRequestDto {
    @NotNull
    private Long id;
}
