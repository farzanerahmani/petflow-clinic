package com.roochi.petflowshared.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@MappedSuperclass
@Getter
@Setter
public abstract class ClinicSoftDeleteEntity extends SoftDeleteEntity {

    @Column(name = "clinic_id", nullable = false, updatable = false)
    private Long clinicId;
}
