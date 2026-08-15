package com.roochi.petflowvisit.dto.request.vaccine;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Schema(name = "SearchVaccineRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class SearchVaccineRequestDto extends PageRequestDto {

    private String code;

    private String name;

    private String disease;

    private Boolean active;
}
