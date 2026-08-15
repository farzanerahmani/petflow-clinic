package com.roochi.petflowvisit.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Data
public class GetVaccineForUpdateRequestDto {

    @NotNull
    private Long vaccineId;
}
