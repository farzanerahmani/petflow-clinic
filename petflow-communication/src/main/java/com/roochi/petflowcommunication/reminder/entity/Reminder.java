package com.roochi.petflowcommunication.reminder.entity;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderStatus;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderType;
import com.roochi.petflowcommunication.template.entity.NotificationTemplate;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Entity
@Table(
        name = "reminder",
        indexes = {
                @Index(
                        name = "idx_reminder_status",
                        columnList = "status"
                ),
                @Index(
                        name = "idx_reminder_scheduled_at",
                        columnList = "scheduled_at"
                ),
                @Index(
                        name = "idx_reminder_reference",
                        columnList = "reference_type, reference_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reminder extends SoftDeleteEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ReminderType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReminderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationChannel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private NotificationTemplate template;

    /**
     * Visit / Appointment / Prescription / ...
     */
    @Column(nullable = false)
    private Long referenceId;

    @Column(nullable = false, length = 50)
    private String referenceType;

    /**
     * دریافت‌کننده
     * مثلاً شماره موبایل، Email یا Push Token
     */
    @Column(nullable = false, length = 255)
    private String recipient;

    /**
     * زمان اجرای Reminder
     */
    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    private LocalDateTime processedAt;

    @Column(length = 500)
    private String description;
}
