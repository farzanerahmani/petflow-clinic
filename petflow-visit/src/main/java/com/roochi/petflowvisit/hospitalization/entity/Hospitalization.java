package com.roochi.petflowvisit.hospitalization.entity;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.hospitalization.entity.enums.HospitalizationStatus;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */


@Entity
@Table(name = "hospitalization")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospitalization extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @Column(nullable = false)
    private LocalDateTime admissionDate;

    @Column
    private LocalDateTime dischargeDate;

    @Column(length = 100)
    private String ward;

    @Column(length = 50)
    private String cageNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private HospitalizationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attending_veterinarian_id")
    private User attendingVeterinarian;

    @Column(length = 1000)
    private String diagnosis;

    @Column(length = 2000)
    private String treatmentPlan;

    @Column(length = 1000)
    private String dischargeSummary;

    @Column(length = 1000)
    private String note;
}
