package com.roochi.petflowcommunication.notification.dto.response;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationPriority;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Builder
public class NotificationResponseDto {

    private Long id;

    private NotificationChannel channel;

    private NotificationStatus status;

    private NotificationPriority priority;

    private String recipient;

    private String subject;

    private String message;

    private Long templateId;

    private Long referenceId;

    private String referenceType;

    private LocalDateTime scheduledAt;

    private LocalDateTime sentAt;

    private LocalDateTime lastAttemptAt;

    private Integer attemptCount;

    private String providerMessageId;

    private String failureReason;
}
