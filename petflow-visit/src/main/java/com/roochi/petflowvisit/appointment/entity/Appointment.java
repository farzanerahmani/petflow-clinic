package com.roochi.petflowvisit.appointment.entity;

import com.roochi.petflowshared.entity.AuditingEntity;
import com.roochi.petflowshared.entity.ClinicSoftDeleteEntity;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import com.roochi.petflowvisit.appointment.entity.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Entity
@Table(
        name = "appointments",
        indexes = {
                @Index(
                        name = "idx_appointment_clinic_date",
                        columnList = "clinic_id, appointment_date"
                ),
                @Index(
                        name = "idx_appointment_doctor_date",
                        columnList = "doctor_user_id, appointment_date"
                ),
                @Index(
                        name = "idx_appointment_pet_date",
                        columnList = "pet_id, appointment_date"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Appointment extends ClinicSoftDeleteEntity {

    @Column(name = "clinic_id", nullable = false, updatable = false)
    private Long clinicId;

    @Column(nullable = false)
    private Long petId;

    @Column(nullable = false)
    private Long doctorUserId;

    @Column(nullable = false)
    private LocalDateTime appointmentDate;

    @Column(length = 1000)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AppointmentStatus status;

    @Column(length = 1000)
    private String description;

    private LocalDateTime confirmedAt;

    private LocalDateTime checkedInAt;

    private LocalDateTime cancelledAt;

    @Column(length = 500)
    private String cancellationReason;

    private LocalDateTime completedAt;
}
