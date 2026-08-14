package com.roochi.petflowcommunication.notification.dto.request;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class CreateNotificationRequestDto {

    @NotNull
    private NotificationChannel channel;

    @NotNull
    private NotificationPriority priority;

    @NotBlank
    private String recipient;

    private String subject;

    @NotBlank
    private String message;

    private Long templateId;

    private Long referenceId;

    private String referenceType;

    private LocalDateTime scheduledAt;
}
