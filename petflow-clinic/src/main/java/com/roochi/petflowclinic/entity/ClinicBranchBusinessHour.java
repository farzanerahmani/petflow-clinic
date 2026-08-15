package com.roochi.petflowclinic.entity;

import com.roochi.petflowshared.entity.BaseEntity;
import com.roochi.petflowclinic.enumeration.WorkingDay;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Entity
@Table(name = "clinic_branch_business_hours")
@Getter
@Setter
public class ClinicBranchBusinessHour extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClinicBranch branch;

    @Enumerated(EnumType.STRING)
    private WorkingDay workingDay;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean closed;
}
