package com.roochi.petflowvisit.dto.response.procedure;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.DrugDto;
import com.roochi.petflowvisit.dto.cmmon.ProcedureDto;
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
public class SearchProcedureResponseDto extends PageResponseDto<ProcedureDto> {

}
