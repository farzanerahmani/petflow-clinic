package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.ActiveEntity;
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
@Table(name = "clinic_branches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicBranch extends ActiveEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic-id", nullable = false)
    private Clinic clinic;

    @Column(nullable = false)
    private String name;

    private String phone;

    private String email;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private Boolean mainBranch = false;

}
