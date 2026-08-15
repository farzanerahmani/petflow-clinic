package com.roochi.petflowvisit.labrequest.entity;

import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.labtest.entity.LabTest;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/20/2026
 */

@Entity
@Table(name = "lab_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabRequest extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lab_test_id", nullable = false)
    private LabTest labTest;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Column
    private LocalDate sampleDate;

    @Column(length = 1000)
    private String note;
}
