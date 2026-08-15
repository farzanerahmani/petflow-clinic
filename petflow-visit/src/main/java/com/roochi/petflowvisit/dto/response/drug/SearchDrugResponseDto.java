package com.roochi.petflowvisit.dto.response.drug;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SearchDrugResponseDto extends PageResponseDto<DrugDto> {

}
