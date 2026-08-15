package com.roochi.petflowcommunication.template.dto.response;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import lombok.Builder;
import lombok.Getter;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Builder
public class NotificationTemplateResponseDto {

    private Long id;

    private String code;

    private String name;

    private NotificationChannel channel;

    private String subjectTemplate;

    private String bodyTemplate;

    private Boolean active;

    private String description;
}
