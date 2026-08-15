package com.roochi.petflowvisit.dto.request.labtest;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Schema(name = "GetAllPetsRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchLabTestRequestDto extends PageRequestDto {

    private String code;

    private String name;

    private Boolean active;
}
