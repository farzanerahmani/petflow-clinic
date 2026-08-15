package com.roochi.petflowvisit.dto.request.labtest;

import com.roochi.petflowvisit.drug.entity.enums.DrugForm;
import com.roochi.petflowvisit.drug.entity.enums.DrugType;
import com.roochi.petflowvisit.drug.entity.enums.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class UpdateLabTestRequestDto {
    @NotNull
    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Boolean active;
}
