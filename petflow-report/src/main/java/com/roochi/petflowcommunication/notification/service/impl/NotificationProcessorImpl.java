package com.roochi.petflowcommunication.notification.service.impl;

import com.roochi.petflowcommunication.notification.channel.NotificationSenderDispatcher;
import com.roochi.petflowcommunication.notification.channel.SendResult;
import com.roochi.petflowcommunication.notification.config.NotificationRetryProperties;
import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationStatus;
import com.roochi.petflowcommunication.notification.repository.NotificationRepository;
import com.roochi.petflowcommunication.notification.service.NotificationProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProcessorImpl
        implements NotificationProcessor {

    private final NotificationRepository notificationRepository;

    private final NotificationSenderDispatcher notificationSenderDispatcher;

    private final NotificationRetryProperties retryProperties;


    @Override
    @Transactional
    public void processPendingNotifications() {

        LocalDateTime now = LocalDateTime.now();

        List<Notification> notifications =
                notificationRepository.findProcessableNotifications(
                        NotificationStatus.PENDING,
                        NotificationStatus.FAILED,
                        retryProperties.getMaxAttempts(),
                        now
                );

        for (Notification notification : notifications) {

            process(notification);
        }
    }


    private void process(Notification notification) {

        try {

            markAsProcessing(notification);

            SendResult result =
                    notificationSenderDispatcher.dispatch(
                            notification
                    );

            if (result.success()) {

                markAsSent(
                        notification,
                        result
                );

                return;
            }

            handleFailure(
                    notification,
                    result.errorMessage()
            );

        } catch (Exception ex) {

            log.error(
                    "Notification sending failed. notificationId={}",
                    notification.getId(),
                    ex
            );

            handleFailure(
                    notification,
                    ex.getMessage()
            );
        }
    }


    private void markAsProcessing(
            Notification notification
    ) {

        int attempts =
                notification.getAttemptCount() == null
                        ? 0
                        : notification.getAttemptCount();

        notification.setStatus(
                NotificationStatus.PROCESSING
        );

        notification.setAttemptCount(
                attempts + 1
        );

        notification.setLastAttemptAt(
                LocalDateTime.now()
        );

        notification.setErrorMessage(null);
    }


    private void markAsSent(
            Notification notification,
            SendResult result
    ) {

        notification.setStatus(
                NotificationStatus.SENT
        );

        notification.setSentAt(
                LocalDateTime.now()
        );

        notification.setProviderMessageId(
                result.providerMessageId()
        );

        notification.setErrorMessage(null);

        notification.setNextAttemptAt(null);
    }


    private void handleFailure(
            Notification notification,
            String errorMessage
    ) {

        int attempts =
                notification.getAttemptCount() == null
                        ? 0
                        : notification.getAttemptCount();

        notification.setErrorMessage(
                errorMessage
        );

        if (attempts >= retryProperties.getMaxAttempts()) {

            notification.setStatus(
                    NotificationStatus.FAILED
            );

            notification.setNextAttemptAt(null);

            log.warn(
                    "Notification permanently failed. notificationId={}, attempts={}",
                    notification.getId(),
                    attempts
            );

            return;
        }

        notification.setStatus(
                NotificationStatus.FAILED
        );

        notification.setNextAttemptAt(
                calculateNextAttempt(attempts)
        );

        log.warn(
                "Notification failed and will be retried. " +
                        "notificationId={}, attempts={}, nextAttemptAt={}",
                notification.getId(),
                attempts,
                notification.getNextAttemptAt()
        );
    }


    private LocalDateTime calculateNextAttempt(
            int attempts
    ) {

        long delaySeconds;

        if (attempts <= 1) {

            delaySeconds =
                    retryProperties.getFirstDelaySeconds();

        } else {

            delaySeconds =
                    retryProperties.getSecondDelaySeconds();
        }

        return LocalDateTime.now()
                .plusSeconds(delaySeconds);
    }
}