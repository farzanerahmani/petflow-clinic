package com.roochi.petflowvisit.dto.request.labtest;

import com.roochi.petflowvisit.drug.entity.enums.DrugForm;
import com.roochi.petflowvisit.drug.entity.enums.DrugType;
import com.roochi.petflowvisit.drug.entity.enums.Unit;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class AddLabTestRequestDto {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}
