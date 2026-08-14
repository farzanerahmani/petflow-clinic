package com.roochi.petflowcommunication.reminder.service.impl;

import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationPriority;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationStatus;
import com.roochi.petflowcommunication.notification.repository.NotificationRepository;
import com.roochi.petflowcommunication.reminder.entity.Reminder;
import com.roochi.petflowcommunication.reminder.entity.enums.ReminderStatus;
import com.roochi.petflowcommunication.reminder.repository.ReminderRepository;
import com.roochi.petflowcommunication.reminder.service.ReminderProcessor;
import com.roochi.petflowcommunication.template.service.TemplateRenderer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReminderProcessorImpl implements ReminderProcessor {

    private final ReminderRepository reminderRepository;

    private final NotificationRepository notificationRepository;

    private final TemplateRenderer templateRenderer;


    @Override
    @Transactional
    public void processDueReminders() {

        LocalDateTime now = LocalDateTime.now();

        List<Reminder> reminders =
                reminderRepository.findDueReminders(
                        ReminderStatus.PENDING,
                        now
                );

        for (Reminder reminder : reminders) {

            try {

                process(reminder);

            } catch (Exception ex) {

                log.error(
                        "Failed to process reminder. reminderId={}",
                        reminder.getId(),
                        ex
                );

                reminder.setStatus(
                        ReminderStatus.FAILED
                );
            }
        }
    }


    private void process(Reminder reminder) {

        Map<String, Object> variables =
                Collections.emptyMap();

        String subject =
                templateRenderer.render(
                        reminder.getTemplate()
                                .getSubjectTemplate(),
                        variables
                );

        String message =
                templateRenderer.render(
                        reminder.getTemplate()
                                .getBodyTemplate(),
                        variables
                );

        Notification notification =
                Notification.builder()
                        .channel(
                                reminder.getChannel()
                        )
                        .status(
                                NotificationStatus.PENDING
                        )
                        .priority(
                                NotificationPriority.NORMAL
                        )
                        .recipient(
                                reminder.getRecipient()
                        )
                        .subject(subject)
                        .message(message)
                        .template(
                                reminder.getTemplate()
                        )
                        .referenceId(
                                reminder.getReferenceId()
                        )
                        .referenceType(
                                reminder.getReferenceType()
                        )
                        .scheduledAt(
                                LocalDateTime.now()
                        )
                        .attemptCount(0)
                        .build();

        notificationRepository.save(notification);

        reminder.setStatus(
                ReminderStatus.PROCESSED
        );

        reminder.setProcessedAt(
                LocalDateTime.now()
        );
    }
}