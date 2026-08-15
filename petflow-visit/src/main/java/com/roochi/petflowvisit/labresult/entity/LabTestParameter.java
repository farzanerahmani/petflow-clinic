package com.roochi.petflowvisit.labresult.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/21/2026
 */
@Entity
@Table(name = "lab_test_parameter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabTestParameter extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_test_id", nullable = false)
    private LabTest labTest;

    @Column(nullable = false, length = 100)
    private String name;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "labTestParameter",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<ReferenceRange> referenceRanges = new ArrayList<>();
}
