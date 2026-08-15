package com.roochi.petflowvisit.drug.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.drug.entity.enums.DrugForm;
import com.roochi.petflowvisit.drug.entity.enums.DrugType;
import com.roochi.petflowvisit.drug.entity.enums.Unit;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/18/2026
 */
@Entity
@Table(name = "drugs")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Drug extends SoftDeleteEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 200)
    private String brandName;

    @Column(length = 100)
    private String genericName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrugType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrugForm form;

    @Column(length = 100)
    private String strength;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    @Column(length = 100)
    private String description;

    @NonNull
    private Boolean prescriptionRequired;

    @Builder.Default
    private Boolean active = true;


}
