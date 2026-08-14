package com.roochi.petflowcommunication.notification.scheduler;

import com.roochi.petflowcommunication.notification.service.NotificationProcessor;
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
public class NotificationScheduler {

    private final NotificationProcessor notificationProcessor;


    @Scheduled(
            fixedDelayString =
                    "${communication.notification.fixed-delay:30000}"
    )
    public void processNotifications() {

        log.debug(
                "Starting notification processing..."
        );

        notificationProcessor.processPendingNotifications();

        log.debug(
                "Notification processing finished."
        );
    }
}
