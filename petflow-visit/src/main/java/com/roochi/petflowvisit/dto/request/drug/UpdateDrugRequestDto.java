package com.roochi.petflowvisit.dto.request.drug;

import com.roochi.petflowvisit.drug.entity.enums.DrugForm;
import com.roochi.petflowvisit.drug.entity.enums.DrugType;
import com.roochi.petflowvisit.drug.entity.enums.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.lang.foreign.SymbolLookup;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
public class UpdateDrugRequestDto {
    @NotNull
    private Long drugId;

    @NotBlank
    private String code;

    @NotBlank
    private String brandName;

    private String genericName;

    @NotNull
    private DrugType type;

    @NotNull
    private DrugForm form;

    private String strength;

    @NotNull
    private Unit unit;

    private String description;

    @NotNull
    private Boolean prescriptionRequired;

    @NotNull
    private Boolean active;
}
