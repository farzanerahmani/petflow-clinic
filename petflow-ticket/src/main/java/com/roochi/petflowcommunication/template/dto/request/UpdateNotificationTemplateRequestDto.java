package com.roochi.petflowcommunication.template.dto.request;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class UpdateNotificationTemplateRequestDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private NotificationChannel channel;

    private String subjectTemplate;

    @NotBlank
    private String bodyTemplate;

    @NotNull
    private Boolean active;

    private String description;
}
