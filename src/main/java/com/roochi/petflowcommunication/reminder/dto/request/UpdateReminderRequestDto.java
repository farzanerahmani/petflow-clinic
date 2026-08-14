package com.roochi.petflowcommunication.reminder.dto.request;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderType;
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
public class UpdateReminderRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private ReminderType type;

    @NotNull
    private NotificationChannel channel;

    @NotNull
    private Long templateId;

    @NotNull
    private Long referenceId;

    @NotNull
    private String referenceType;

    @NotNull
    private String recipient;

    @NotNull
    private LocalDateTime scheduledAt;

    private String description;
}
