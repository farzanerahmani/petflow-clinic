package com.roochi.petflowcommunication.template.entity;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowshared.entity.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Entity
@Table(
        name = "notification_template",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "code",
                                "channel"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTemplate extends SoftDeleteEntity {

    @Column(nullable = false, length = 100)
    private String code;

    @Column(nullable = false, length = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationChannel channel;

    /**
     * برای Email
     */
    @Column(length = 500)
    private String subjectTemplate;

    /**
     * متن Template
     *
     * مثال:
     *
     * سلام {{patientName}}
     * نوبت شما در تاریخ {{appointmentDate}} است.
     */
    @Column(nullable = false, length = 10000)
    private String bodyTemplate;

    @Column(nullable = false)
    private Boolean active;

    @Column(length = 500)
    private String description;
}
