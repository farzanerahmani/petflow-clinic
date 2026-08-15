package com.roochi.petflowaudit.entity;

import com.roochi.petflowaudit.entity.enums.AuditAction;
import com.roochi.petflowaudit.entity.enums.AuditEntityType;
import com.roochi.petflowshared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */


@Entity
@Table(
        name = "audit_logs",
        indexes = {

                @Index(
                        name = "idx_audit_clinic_created",
                        columnList = "clinic_id, created_at"
                ),

                @Index(
                        name = "idx_audit_user_created",
                        columnList = "user_id, created_at"
                ),

                @Index(
                        name = "idx_audit_entity",
                        columnList = "entity_type, entity_id"
                ),

                @Index(
                        name = "idx_audit_action",
                        columnList = "action"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog extends BaseEntity {

    /**
     * کلینیکی که عملیات در آن اتفاق افتاده.
     *
     * برای عملیات‌های Clinic-level مقدار دارد.
     */
    @Column(name = "clinic_id")
    private Long clinicId;


    /**
     * کاربری که عملیات را انجام داده.
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * نوع عملیات.
     */
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private AuditAction action;


    /**
     * نوع Entity که تغییر کرده.
     */
    @Enumerated(EnumType.STRING)
    @Column(
            name = "entity_type",
            nullable = false,
            length = 50
    )
    private AuditEntityType entityType;


    /**
     * ID رکوردی که روی آن عملیات انجام شده.
     */
    @Column(name = "entity_id")
    private Long entityId;


    /**
     * توضیح قابل خواندن برای Audit.
     *
     * مثال:
     * "Appointment cancelled"
     */
    @Column(length = 500)
    private String description;


    /**
     * وضعیت قبل از تغییر.
     *
     * JSON string
     */
    @Lob
    @Column(name = "old_value")
    private String oldValue;


    /**
     * وضعیت بعد از تغییر.
     *
     * JSON string
     */
    @Lob
    @Column(name = "new_value")
    private String newValue;


    /**
     * IP کاربر.
     */
    @Column(name = "ip_address", length = 45)
    private String ipAddress;


    /**
     * User-Agent مرورگر یا Client.
     */
    @Column(
            name = "user_agent",
            length = 1000
    )
    private String userAgent;


    /**
     * زمان دقیق رخداد.
     *
     * جدا از createdAt نگه می‌داریم تا
     * مفهوم Audit واضح باشد.
     */
    @Column(
            name = "event_at",
            nullable = false
    )
    private LocalDateTime eventAt;
}
