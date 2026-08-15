package com.roochi.petflowvisit.visit.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import com.roochi.petflowvisit.visit.entity.enums.VisitStatus;
import com.roochi.petflowvisit.visit.entity.enums.VisitType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/10/2026
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "visits")
public class Visit extends AuditingEntity {

    @Column(nullable = false)
    private Long clinicId;

    @Column(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private Long doctorUserId;

    @Column(nullable = false)
    private LocalDateTime visitDate;

    @Column(length = 1000)
    private String chiefComplaint;

    @Column(length = 2000)
    private String diagnosis;

    @Column(length = 2000)
    private String description;

    private Double weight;

    private Double temperature;

    private Integer heartRate;

    private Integer respiratoryRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitType type;
    /**
     * Appointment that caused this visit.
     * <p>
     * Nullable because a Visit can potentially be created
     * without a prior appointment.
     */
    @Column
    private Long appointmentId;

    private LocalDateTime finishedAt;
}
