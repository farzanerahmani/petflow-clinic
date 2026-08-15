package com.roochi.petflowpet.dto.response;

import com.roochi.petflowpet.dto.PetDto;
import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchPetResponseDto extends PageResponseDto<PetDto>{
}
