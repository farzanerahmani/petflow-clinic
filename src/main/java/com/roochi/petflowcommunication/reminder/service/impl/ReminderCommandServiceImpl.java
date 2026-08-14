package com.roochi.petflowcommunication.reminder.service.impl;

import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.reminder.dto.request.CreateReminderRequestDto;
import com.roochi.petflowcommunication.reminder.dto.request.UpdateReminderRequestDto;
import com.roochi.petflowcommunication.reminder.dto.response.ReminderResponseDto;
import com.roochi.petflowcommunication.reminder.entity.Reminder;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderStatus;
import com.roochi.petflowcommunication.reminder.repository.ReminderRepository;
import com.roochi.petflowcommunication.reminder.service.command.ReminderCommandService;
import com.roochi.petflowcommunication.template.entity.NotificationTemplate;
import com.roochi.petflowcommunication.template.repository.NotificationTemplateRepository;
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
public class ReminderCommandServiceImpl
        implements ReminderCommandService {

    private final ReminderRepository reminderRepository;

    private final NotificationTemplateRepository templateRepository;


    @Override
    public ReminderResponseDto create(
            CreateReminderRequestDto request
    ) {

        NotificationTemplate template =
                findTemplate(
                        request.getTemplateId()
                );

        validateChannel(
                request.getChannel(),
                template
        );

        Reminder reminder =
                Reminder.builder()
                        .type(request.getType())
                        .status(ReminderStatus.PENDING)
                        .channel(request.getChannel())
                        .template(template)
                        .referenceId(
                                request.getReferenceId()
                        )
                        .referenceType(
                                request.getReferenceType()
                        )
                        .recipient(
                                request.getRecipient()
                        )
                        .scheduledAt(
                                request.getScheduledAt()
                        )
                        .description(
                                request.getDescription()
                        )
                        .build();

        reminderRepository.save(reminder);

        return map(reminder);
    }


    @Override
    public ReminderResponseDto update(
            UpdateReminderRequestDto request
    ) {

        Reminder reminder =
                reminderRepository
                        .findActiveById(request.getId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (reminder.getStatus()
                != ReminderStatus.PENDING) {

            throw new IllegalStateException(
                    "Only pending reminder can be updated."
            );
        }

        NotificationTemplate template =
                findTemplate(
                        request.getTemplateId()
                );

        validateChannel(
                request.getChannel(),
                template
        );

        reminder.setType(request.getType());

        reminder.setChannel(request.getChannel());

        reminder.setTemplate(template);

        reminder.setReferenceId(
                request.getReferenceId()
        );

        reminder.setReferenceType(
                request.getReferenceType()
        );

        reminder.setRecipient(
                request.getRecipient()
        );

        reminder.setScheduledAt(
                request.getScheduledAt()
        );

        reminder.setDescription(
                request.getDescription()
        );

        return map(reminder);
    }


    @Override
    public void cancel(Long id) {

        Reminder reminder =
                reminderRepository
                        .findActiveById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (reminder.getStatus()
                != ReminderStatus.PENDING) {

            throw new IllegalStateException(
                    "Only pending reminder can be cancelled."
            );
        }

        reminder.setStatus(
                ReminderStatus.CANCELLED
        );
    }


    private NotificationTemplate findTemplate(
            Long templateId
    ) {

        return templateRepository.findById(templateId)
                .filter(template ->
                        !Boolean.TRUE.equals(
                                template.getDeleted()
                        )
                )
                .filter(template ->
                        Boolean.TRUE.equals(
                                template.getActive()
                        )
                )
                .orElseThrow(() ->
                        new NotFoundException(
                                ErrorCode.INTERNAL_ERROR
                        )
                );
    }


    private void validateChannel(
            NotificationChannel channel,
            NotificationTemplate template
    ) {

        if (channel != template.getChannel()) {

            throw new IllegalArgumentException(
                    "Reminder channel does not match template channel."
            );
        }
    }


    private ReminderResponseDto map(
            Reminder reminder
    ) {

        return ReminderResponseDto.builder()
                .id(reminder.getId())
                .type(reminder.getType())
                .status(reminder.getStatus())
                .channel(reminder.getChannel())
                .templateId(
                        reminder.getTemplate() != null
                                ? reminder.getTemplate().getId()
                                : null
                )
                .referenceId(
                        reminder.getReferenceId()
                )
                .referenceType(
                        reminder.getReferenceType()
                )
                .recipient(
                        reminder.getRecipient()
                )
                .scheduledAt(
                        reminder.getScheduledAt()
                )
                .processedAt(
                        reminder.getProcessedAt()
                )
                .description(
                        reminder.getDescription()
                )
                .build();
    }
}
