package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
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
@Table(name = "clinic_holidays")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicHoliday extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id",nullable = false)
    private ClinicBranch branch;

    @Column(nullable = false)
    private LocalDate holidayDate;

    @Column(length = 300)
    private String description;
}
