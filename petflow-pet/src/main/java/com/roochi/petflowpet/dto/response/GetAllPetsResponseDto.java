package com.roochi.petflowpet.dto.response;


import com.roochi.petflowpet.dto.PetDto;
import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 6/26/2026
 */
@Schema(name = "GetAllPetsResponseDto")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetAllPetsResponseDto extends PageResponseDto<PetDto> {
}
