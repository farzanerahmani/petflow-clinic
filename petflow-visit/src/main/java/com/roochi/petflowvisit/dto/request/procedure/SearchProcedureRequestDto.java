package com.roochi.petflowvisit.dto.request.procedure;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/23/2026
 */
@Schema(name = "SearchProcedureRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchProcedureRequestDto extends PageRequestDto {
    private String code;

    private String name;

    private Boolean active;
}
