package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.ActiveEntity;
import com.roochi.petflowclinic.enumeration.ContactType;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "clinic_contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicContact extends ActiveEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    public Clinic clinic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ContactType type;

    @Column(nullable = false)
    private String value;

    @Column(length = 150)
    private String title;

    @Column(nullable = false)
    private Boolean primaryContact = false;
}
