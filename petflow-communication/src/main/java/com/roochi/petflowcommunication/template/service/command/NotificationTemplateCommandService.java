package com.roochi.petflowcommunication.template.service.command;

import com.roochi.petflowcommunication.template.dto.request.CreateNotificationTemplateRequestDto;
import com.roochi.petflowcommunication.template.dto.request.UpdateNotificationTemplateRequestDto;
import com.roochi.petflowcommunication.template.dto.response.NotificationTemplateResponseDto;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


public interface NotificationTemplateCommandService {

    NotificationTemplateResponseDto create(
            CreateNotificationTemplateRequestDto request
    );

    NotificationTemplateResponseDto update(
            UpdateNotificationTemplateRequestDto request
    );

    void delete(Long id);

    void activate(Long id);

    void deactivate(Long id);
}
