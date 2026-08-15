package com.roochi.petflowvisit.labtest.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.labresult.entity.LabTestParameter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/19/2026
 */
@Entity
@Table(name = "lab_test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabTest extends SoftDeleteEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "labTest", fetch = FetchType.LAZY)
    @Builder.Default
    private List<LabTestParameter> parameters = new ArrayList<>();
}
