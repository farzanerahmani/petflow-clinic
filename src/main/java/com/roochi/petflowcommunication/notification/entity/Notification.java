package com.roochi.petflowcommunication.notification.entity;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationPriority;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationStatus;
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
        name = "notification",
        indexes = {
                @Index(
                        name = "idx_notification_status",
                        columnList = "status"
                ),
                @Index(
                        name = "idx_notification_channel",
                        columnList = "channel"
                ),
                @Index(
                        name = "idx_notification_scheduled_at",
                        columnList = "scheduled_at"
                ),
                @Index(
                        name = "idx_notification_recipient",
                        columnList = "recipient"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends SoftDeleteEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationChannel channel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationPriority priority;

    /**
     * شماره موبایل، ایمیل یا Push Token
     */
    @Column(nullable = false, length = 255)
    private String recipient;

    /**
     * برای Email استفاده می‌شود.
     */
    @Column(length = 255)
    private String subject;

    @Column(nullable = false, length = 5000)
    private String message;

    /**
     * Template استفاده شده برای Notification
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private NotificationTemplate template;

    /**
     * شناسه رکوردی که باعث ایجاد Notification شده.
     * مثلاً Visit، Prescription، Appointment و ...
     */
    private Long referenceId;

    /**
     * نوع Reference
     * مثلاً APPOINTMENT، PRESCRIPTION و ...
     */
    @Column(length = 50)
    private String referenceType;

    /**
     * زمان برنامه‌ریزی ارسال
     */
    private LocalDateTime scheduledAt;

    /**
     * زمان واقعی ارسال
     */
    private LocalDateTime sentAt;

    /**
     * زمان آخرین تلاش
     */
    private LocalDateTime lastAttemptAt;

    /**
     * تعداد تلاش‌های ارسال
     */
    @Column(nullable = false)
    private Integer attemptCount;

    /**
     * شناسه‌ای که Provider برمی‌گرداند.
     */
    @Column(length = 255)
    private String providerMessageId;

    /**
     * خطای آخرین ارسال
     */
    @Column(length = 2000)
    private String failureReason;

    @Column(length = 1000)
    private String errorMessage;

    @Column
    private LocalDateTime nextAttemptAt;
}