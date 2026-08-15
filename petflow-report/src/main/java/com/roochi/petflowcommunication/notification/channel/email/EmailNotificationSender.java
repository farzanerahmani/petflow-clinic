package com.roochi.petflowcommunication.notification.channel.email;

import com.roochi.petflowcommunication.notification.channel.NotificationSender;
import com.roochi.petflowcommunication.notification.channel.SendResult;
import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.notification.provider.email.EmailProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Component
@RequiredArgsConstructor
public class EmailNotificationSender
        implements NotificationSender {

    private final EmailProvider emailProvider;

    @Override
    public NotificationChannel supportedChannel() {

        return NotificationChannel.EMAIL;
    }

    @Override
    public SendResult send(
            Notification notification
    ) {

        String providerMessageId =
                emailProvider.send(
                        notification.getRecipient(),
                        notification.getSubject(),
                        notification.getMessage()
                );

        return SendResult.success(
                providerMessageId
        );
    }
}