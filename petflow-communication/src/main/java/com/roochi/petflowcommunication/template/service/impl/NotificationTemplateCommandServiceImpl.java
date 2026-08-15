package com.roochi.petflowcommunication.template.service.impl;

import com.roochi.petflowcommunication.template.dto.request.CreateNotificationTemplateRequestDto;
import com.roochi.petflowcommunication.template.dto.request.UpdateNotificationTemplateRequestDto;
import com.roochi.petflowcommunication.template.dto.response.NotificationTemplateResponseDto;
import com.roochi.petflowcommunication.template.entity.NotificationTemplate;
import com.roochi.petflowcommunication.template.repository.NotificationTemplateRepository;
import com.roochi.petflowcommunication.template.service.command.NotificationTemplateCommandService;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Service
@RequiredArgsConstructor
@Transactional
public class NotificationTemplateCommandServiceImpl
        implements NotificationTemplateCommandService {

    private final NotificationTemplateRepository templateRepository;


    @Override
    public NotificationTemplateResponseDto create(
            CreateNotificationTemplateRequestDto request
    ) {

        NotificationTemplate template =
                NotificationTemplate.builder()
                        .code(request.getCode())
                        .name(request.getName())
                        .channel(request.getChannel())
                        .subjectTemplate(
                                request.getSubjectTemplate()
                        )
                        .bodyTemplate(
                                request.getBodyTemplate()
                        )
                        .active(true)
                        .description(
                                request.getDescription()
                        )
                        .build();

        templateRepository.save(template);

        return map(template);
    }


    @Override
    public NotificationTemplateResponseDto update(
            UpdateNotificationTemplateRequestDto request
    ) {

        NotificationTemplate template =
                templateRepository.findById(request.getId())
                        .filter(t ->
                                !Boolean.TRUE.equals(
                                        t.getDeleted()
                                )
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        template.setName(request.getName());

        template.setChannel(request.getChannel());

        template.setSubjectTemplate(
                request.getSubjectTemplate()
        );

        template.setBodyTemplate(
                request.getBodyTemplate()
        );

        template.setActive(
                request.getActive()
        );

        template.setDescription(
                request.getDescription()
        );

        return map(template);
    }


    @Override
    public void delete(Long id) {

        NotificationTemplate template =
                templateRepository.findById(id)
                        .filter(t ->
                                !Boolean.TRUE.equals(
                                        t.getDeleted()
                                )
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        template.setDeleted(true);
    }


    @Override
    public void activate(Long id) {

        NotificationTemplate template =
                find(id);

        template.setActive(true);
    }


    @Override
    public void deactivate(Long id) {

        NotificationTemplate template =
                find(id);

        template.setActive(false);
    }


    private NotificationTemplate find(Long id) {

        return templateRepository.findById(id)
                .filter(t ->
                        !Boolean.TRUE.equals(
                                t.getDeleted()
                        )
                )
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorCode.INTERNAL_ERROR
                        )
                );
    }


    private NotificationTemplateResponseDto map(
            NotificationTemplate template
    ) {

        return NotificationTemplateResponseDto.builder()
                .id(template.getId())
                .code(template.getCode())
                .name(template.getName())
                .channel(template.getChannel())
                .subjectTemplate(
                        template.getSubjectTemplate()
                )
                .bodyTemplate(
                        template.getBodyTemplate()
                )
                .active(template.getActive())
                .description(template.getDescription())
                .build();
    }
}
