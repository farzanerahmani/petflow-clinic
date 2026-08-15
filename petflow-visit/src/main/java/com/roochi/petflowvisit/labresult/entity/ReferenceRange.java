package com.roochi.petflowvisit.labresult.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */


@Entity
@Table(name = "reference_range")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferenceRange extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_test_parameter_id", nullable = false)
    private LabTestParameter labTestParameter;

    @Column(nullable = false, length = 100)
    private String species;

    @Column(length = 100)
    private String breed;

    @Column
    private Integer minAgeInMonth;

    @Column
    private Integer maxAgeInMonth;

    @Column(length = 20)
    private String gender;

    @Column(length = 100)
    private String minimumValue;

    @Column(length = 100)
    private String maximumValue;

    @Column(length = 50)
    private String unit;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;
}
