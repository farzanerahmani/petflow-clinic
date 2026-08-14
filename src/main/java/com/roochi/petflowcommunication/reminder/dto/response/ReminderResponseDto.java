package com.roochi.petflowcommunication.reminder.dto.response;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderStatus;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Getter
@Builder
public class ReminderResponseDto {

    private Long id;

    private ReminderType type;

    private ReminderStatus status;

    private NotificationChannel channel;

    private Long templateId;

    private Long referenceId;

    private String referenceType;

    private String recipient;

    private LocalDateTime scheduledAt;

    private LocalDateTime processedAt;

    private String description;
}
