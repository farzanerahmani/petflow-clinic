package com.roochi.petflowvisit.prescription.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author farzane.rahmani
 * @created 7/12/2026
 */
@Entity
@Table(name = "prescription_items")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionItem extends AuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "prescription_id",nullable = false)
    private Prescription prescription;

    @Column(name = "drug_name",nullable = false,length = 200)
    private Long drugId;

    @Column(name = "dosage",length = 100)
    private BigDecimal dosage;

    @Column(name = "unit",length = 50)
    private Long doseUnitId;

    @Column(name = "frequency",length = 100)
    private Long frequencyId;

    @Column(name = "duration",length = 100)
    private Integer duration;

    private Long durationUnitId;

    @Column(name = "instruction",length = 1000)
    private String instruction;
}
