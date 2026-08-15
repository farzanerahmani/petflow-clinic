package com.roochi.petflowvisit.dto.request.vaccination;

import com.roochi.petflowshared.mapper.pagination.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */
@Schema(name = "GetAllPetsRequestDto")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class GetAllVaccinationByVisitIdRequestDto extends PageRequestDto {
    @NotNull
    private Long visitId;
}
