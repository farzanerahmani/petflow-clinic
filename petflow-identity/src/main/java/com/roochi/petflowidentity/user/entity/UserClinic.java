package com.roochi.petflowidentity.user.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "user_clinics")
@Getter
@Setter
@NoArgsConstructor
public class UserClinic extends SoftDeleteEntity {


    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean defaultClinic = false;

}
