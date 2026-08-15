package com.roochi.petflowpet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 6/26/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPetByIdRequestDto {
    private Long id;
}
