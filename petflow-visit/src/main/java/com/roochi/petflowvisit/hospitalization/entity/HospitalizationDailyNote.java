package com.roochi.petflowvisit.hospitalization.entity;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/25/2026
 */


@Entity
@Table(name = "hospitalization_daily_note")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalizationDailyNote extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospitalization_id", nullable = false)
    private Hospitalization hospitalization;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private User veterinarian;

    @Column(nullable = false)
    private LocalDateTime recordDateTime;

    @Column(precision = 4, scale = 1)
    private BigDecimal temperature;

    @Column
    private Integer pulse;

    @Column
    private Integer respiration;

    @Column(precision = 6, scale = 2)
    private BigDecimal weight;

    @Column(length = 200)
    private String appetite;

    @Column(length = 200)
    private String urination;

    @Column(length = 200)
    private String defecation;

    @Column(length = 1000)
    private String medication;

    @Column(length = 2000)
    private String note;

    @Column
    private Integer heartRate;

    @Column
    private Integer capillaryRefillTime;

    @Column
    private String mucousMembraneColor;
}