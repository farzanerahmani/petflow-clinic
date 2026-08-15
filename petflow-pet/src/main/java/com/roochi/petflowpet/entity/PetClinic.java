package com.roochi.petflowpet.entity;

import com.roochi.petflowpet.entity.enumeration.PetClinicStatus;
import com.roochi.petflowshared.entity.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/8/2026
 */
@Entity
@Getter
@Setter
@Table(name = "pet_clinics")
public class PetClinic extends AuditingEntity {

    @Column(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private Long clinicId;

    @Column(nullable = false)
    private LocalDate joinedAt;

    private LocalDate leftAt;

    @Enumerated(EnumType.STRING)
    private PetClinicStatus status;
}
