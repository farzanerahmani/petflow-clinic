package com.roochi.petflowcommunication.notification.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Getter
@Setter
public class SendNotificationRequestDto {

    @NotNull
    private Long notificationId;
}
