package com.roochi.petflowvisit.dto.cmmon;

import com.roochi.petflowvisit.drug.entity.enums.DrugForm;
import com.roochi.petflowvisit.drug.entity.enums.DrugType;
import com.roochi.petflowvisit.drug.entity.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {

    private Long drugId;

    private String code;

    private String genericName;

    private String brandName;

    private DrugType type;

    private DrugForm form;

    private String strength;

    private Unit unit;

    private String description;

    private Boolean prescriptionRequired;
}
