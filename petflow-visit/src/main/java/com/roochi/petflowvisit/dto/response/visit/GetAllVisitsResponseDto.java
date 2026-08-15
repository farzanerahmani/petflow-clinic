package com.roochi.petflowvisit.dto.response.visit;

import com.roochi.petflowshared.mapper.pagination.PageResponseDto;
import com.roochi.petflowvisit.dto.cmmon.VisitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Schema(name = "GetAllVisitsResponseDto")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetAllVisitsResponseDto extends PageResponseDto<VisitDto> {
}