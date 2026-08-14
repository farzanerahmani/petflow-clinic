package com.roochi.petflowcommunication.notification.service.command;

import com.roochi.petflowcommunication.notification.dto.request.*;
import com.roochi.petflowcommunication.notification.dto.response.NotificationResponseDto;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface NotificationCommandService {

    NotificationResponseDto create(CreateNotificationRequestDto request);

    NotificationResponseDto send(SendNotificationRequestDto request);

    NotificationResponseDto cancel(Long notificationId);
}
