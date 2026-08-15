package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.ClinicSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "clinic_services",
        uniqueConstraints = {
                @UniqueConstraint(columnNames =
                        {"clinic_id", "service_type_id"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicService extends ClinicSoftDeleteEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id",nullable = false)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id",nullable = false)
    private ServiceType serviceType;

    @Column(nullable = false)
    private Boolean active = true;

}
