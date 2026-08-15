package com.roochi.petflowcommunication.notification.service.impl;

import com.roochi.petflowcommunication.notification.channel.NotificationSenderDispatcher;
import com.roochi.petflowcommunication.notification.channel.SendResult;
import com.roochi.petflowcommunication.notification.dto.request.CreateNotificationRequestDto;
import com.roochi.petflowcommunication.notification.dto.request.SendNotificationRequestDto;
import com.roochi.petflowcommunication.notification.dto.response.NotificationResponseDto;
import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationStatus;
import com.roochi.petflowcommunication.notification.repository.NotificationRepository;
import com.roochi.petflowcommunication.notification.service.command.NotificationCommandService;
import com.roochi.petflowcommunication.template.entity.NotificationTemplate;
import com.roochi.petflowcommunication.template.repository.NotificationTemplateRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Service
@RequiredArgsConstructor
@Transactional
public class NotificationCommandServiceImpl
        implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    private final NotificationTemplateRepository templateRepository;

    private final NotificationSenderDispatcher senderDispatcher;


    @Override
    public NotificationResponseDto create(
            CreateNotificationRequestDto request
    ) {

        NotificationTemplate template = null;

        if (request.getTemplateId() != null) {

            template =
                    templateRepository.findById(
                                    request.getTemplateId()
                            )
                            .filter(t ->
                                    Boolean.TRUE.equals(
                                            t.getActive()
                                    )
                            )
                            .orElseThrow(() ->
                                    new NotFoundException(
                                            ErrorCode.INTERNAL_ERROR
                                    )
                            );
        }

        if (template != null &&
                template.getChannel() != request.getChannel()) {

            throw new IllegalArgumentException(
                    "Notification channel does not match template channel."
            );
        }

        Notification notification =
                Notification.builder()
                        .channel(request.getChannel())
                        .priority(request.getPriority())
                        .status(NotificationStatus.PENDING)
                        .recipient(request.getRecipient())
                        .subject(request.getSubject())
                        .message(request.getMessage())
                        .template(template)
                        .referenceId(request.getReferenceId())
                        .referenceType(request.getReferenceType())
                        .scheduledAt(request.getScheduledAt())
                        .attemptCount(0)
                        .build();

        notificationRepository.save(notification);

        return map(notification);
    }


    @Override
    public NotificationResponseDto send(
            SendNotificationRequestDto request
    ) {

        Notification notification =
                notificationRepository
                        .findActiveById(
                                request.getNotificationId()
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (notification.getStatus()
                == NotificationStatus.SENT) {

            return map(notification);
        }

        if (notification.getStatus()
                == NotificationStatus.CANCELLED) {

            throw new IllegalStateException(
                    "Notification is cancelled."
            );
        }

        LocalDateTime now =
                LocalDateTime.now();

        notification.setStatus(
                NotificationStatus.PROCESSING
        );

        notification.setLastAttemptAt(now);

        notification.setAttemptCount(
                notification.getAttemptCount() + 1
        );

        SendResult result =
                senderDispatcher.dispatch(
                        notification
                );

        if (result.isSuccessful()) {

            notification.setStatus(
                    NotificationStatus.SENT
            );

            notification.setSentAt(now);

            notification.setProviderMessageId(
                    result.getProviderMessageId()
            );

            notification.setFailureReason(null);

        } else {

            notification.setStatus(
                    NotificationStatus.FAILED
            );

            notification.setFailureReason(
                    result.getFailureReason()
            );
        }

        return map(notification);
    }


    @Override
    public NotificationResponseDto cancel(
            Long notificationId
    ) {

        Notification notification =
                notificationRepository
                        .findActiveById(
                                notificationId
                        )
                        .orElseThrow(() ->
                                new NotFoundException(
                                        ErrorCode.INTERNAL_ERROR
                                )
                        );

        if (notification.getStatus()
                == NotificationStatus.SENT) {

            throw new IllegalStateException(
                    "Sent notification cannot be cancelled."
            );
        }

        notification.setStatus(
                NotificationStatus.CANCELLED
        );

        return map(notification);
    }


    private NotificationResponseDto map(
            Notification notification
    ) {

        return NotificationResponseDto.builder()
                .id(notification.getId())
                .channel(notification.getChannel())
                .status(notification.getStatus())
                .priority(notification.getPriority())
                .recipient(notification.getRecipient())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .templateId(
                        notification.getTemplate() != null
                                ? notification.getTemplate().getId()
                                : null
                )
                .referenceId(notification.getReferenceId())
                .referenceType(notification.getReferenceType())
                .scheduledAt(notification.getScheduledAt())
                .sentAt(notification.getSentAt())
                .lastAttemptAt(
                        notification.getLastAttemptAt()
                )
                .attemptCount(
                        notification.getAttemptCount()
                )
                .providerMessageId(
                        notification.getProviderMessageId()
                )
                .failureReason(
                        notification.getFailureReason()
                )
                .build();
    }
}
