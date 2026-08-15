package com.roochi.petflowvisit.dto.request.drug;

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
public class AddDrugRequestDto {
    @NotBlank
    private String code;

    @NotBlank
    private String brandName;

    @NotBlank
    private String genericName;

    @NonNull
    private DrugType type;

    @NonNull
    private DrugForm form;

    @NotBlank
    private String strength;

    @NotBlank
    private Unit unit;

    private String description;

    @NonNull
    private Boolean prescriptionRequired;
}
