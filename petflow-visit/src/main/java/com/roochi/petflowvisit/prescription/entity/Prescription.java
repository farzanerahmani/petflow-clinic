package com.roochi.petflowvisit.prescription.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
@Entity
@Table(name = "prescriptions")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prescription extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "visit_id",nullable = false)
    private Visit visit;

    @Column(name = "description",length = 2000)
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "prescription",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PrescriptionItem> items = new ArrayList<>();
}
