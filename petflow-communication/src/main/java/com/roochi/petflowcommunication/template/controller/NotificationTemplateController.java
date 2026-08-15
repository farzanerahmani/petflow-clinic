package com.roochi.petflowcommunication.template.controller;

import com.roochi.petflowcommunication.template.dto.request.CreateNotificationTemplateRequestDto;
import com.roochi.petflowcommunication.template.dto.request.UpdateNotificationTemplateRequestDto;
import com.roochi.petflowcommunication.template.dto.response.NotificationTemplateResponseDto;
import com.roochi.petflowcommunication.template.service.command.NotificationTemplateCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@RestController
@RequestMapping("/api/communication/templates")
@RequiredArgsConstructor
public class NotificationTemplateController {

    private final NotificationTemplateCommandService templateService;


    @PostMapping
    public NotificationTemplateResponseDto create(
            @Valid
            @RequestBody
            CreateNotificationTemplateRequestDto request
    ) {

        return templateService.create(request);
    }


    @PutMapping
    public NotificationTemplateResponseDto update(
            @Valid
            @RequestBody
            UpdateNotificationTemplateRequestDto request
    ) {

        return templateService.update(request);
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        templateService.delete(id);
    }


    @PatchMapping("/{id}/activate")
    public void activate(
            @PathVariable Long id
    ) {

        templateService.activate(id);
    }


    @PatchMapping("/{id}/deactivate")
    public void deactivate(
            @PathVariable Long id
    ) {

        templateService.deactivate(id);
    }
}
