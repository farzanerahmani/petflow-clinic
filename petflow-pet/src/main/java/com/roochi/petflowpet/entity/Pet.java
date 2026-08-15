package com.roochi.petflowpet.entity;


import com.roochi.petflowpet.entity.enumeration.Gender;
import com.roochi.petflowpet.entity.enumeration.PetStatus;
import com.roochi.petflowpet.entity.enumeration.PetSpecies;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 6/2/2026
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pets")
public class Pet extends SoftDeleteEntity {

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private PetSpecies species;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private BigDecimal weight;

    private LocalDate birthDate; // ager peti mord baraye sahebesh payam tasliat ferstade beshe va pishnehad sarparasti va kharid pet moshbeh

    @Column(length = 100)
    private String microchipId;

    @Enumerated(EnumType.STRING)
    private PetStatus status;
}
