package com.roochi.petflowpet.dto.response;


import com.roochi.petflowpet.dto.PetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 6/26/2026
 */
@Data
public class GetPetByIdResponseDto {
    @Schema(description = "${GetClinicByIdResponseDto.clinic}")
    private PetDto pet;
}
