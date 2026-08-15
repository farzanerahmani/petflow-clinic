package com.roochi.petflowvisit.dto.response.vaccine;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.VaccineDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchVaccineResponseDto extends PageResponseDto<VaccineDto> {

}
