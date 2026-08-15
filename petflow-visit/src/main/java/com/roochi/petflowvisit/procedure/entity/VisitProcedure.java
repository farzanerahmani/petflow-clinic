package com.roochi.petflowvisit.procedure.entity;

import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author farzane.rahmani
 * @created 7/24/2026
 */
@Entity
@Table(name = "visit_procedure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitProcedure extends SoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "procedure_id", nullable = false)
    private Procedure procedure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performed_by")
    private User performedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assistant_id")
    private User assistant;

    @Column(nullable = false)
    private LocalDate performedDate;

    @Column(precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(length = 1000)
    private String note;

    @Column
    private Integer durationMinutes;
}
