package com.roochi.petflowcommunication.notification.controller;

import com.roochi.petflowcommunication.notification.dto.request.CreateNotificationRequestDto;
import com.roochi.petflowcommunication.notification.dto.request.SendNotificationRequestDto;
import com.roochi.petflowcommunication.notification.dto.response.NotificationResponseDto;
import com.roochi.petflowcommunication.notification.service.command.NotificationCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@RestController
@RequestMapping("/api/communication/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationCommandService notificationCommandService;


    @PostMapping
    public NotificationResponseDto create(
            @Valid
            @RequestBody
            CreateNotificationRequestDto request
    ) {

        return notificationCommandService.create(
                request
        );
    }


    @PostMapping("/send")
    public NotificationResponseDto send(
            @Valid
            @RequestBody
            SendNotificationRequestDto request
    ) {

        return notificationCommandService.send(
                request
        );
    }


    @PatchMapping("/{notificationId}/cancel")
    public NotificationResponseDto cancel(
            @PathVariable Long notificationId
    ) {

        return notificationCommandService.cancel(
                notificationId
        );
    }
}
