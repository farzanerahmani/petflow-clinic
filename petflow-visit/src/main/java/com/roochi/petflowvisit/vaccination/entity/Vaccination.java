package com.roochi.petflowvisit.vaccination.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.vaccine.entity.Vaccine;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccination")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccination extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @Column(nullable = false)
    private LocalDate administrationDate;

    private LocalDate nextDueDate;

    @Column(length = 100)
    private String batchNumber;

    @Column(length = 100)
    private String administrationRoute;

    @Column(length = 1000)
    private String note;
}