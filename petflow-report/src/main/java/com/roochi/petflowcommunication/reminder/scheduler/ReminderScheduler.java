package com.roochi.petflowcommunication.reminder.scheduler;

import com.roochi.petflowcommunication.reminder.service.ReminderProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final ReminderProcessor reminderProcessor;


    @Scheduled(fixedDelayString = "${communication.reminder.fixed-delay:30000}")
    public void processReminders() {

        log.debug("Starting reminder processing...");

        reminderProcessor.processDueReminders();

        log.debug("Reminder processing finished.");
    }
}
