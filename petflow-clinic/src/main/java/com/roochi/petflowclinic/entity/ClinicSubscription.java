package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.ActiveEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "clinic_subscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicSubscription extends ActiveEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id",nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate expireDate;

}
