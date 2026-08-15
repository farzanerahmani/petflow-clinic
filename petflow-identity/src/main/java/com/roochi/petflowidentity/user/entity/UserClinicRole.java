package com.roochi.petflowidentity.user.entity;

import com.roochi.petflowshared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "user_clinic_roles")
@Getter
@Setter
@NoArgsConstructor
public class UserClinicRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_clinic_id",nullable = false)
    private UserClinic userClinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;
}
