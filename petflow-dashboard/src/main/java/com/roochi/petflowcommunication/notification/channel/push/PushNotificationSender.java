package com.roochi.petflowcommunication.notification.channel.push;

import com.roochi.petflowcommunication.notification.channel.NotificationSender;
import com.roochi.petflowcommunication.notification.channel.SendResult;
import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.notification.provider.push.PushProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Component
@RequiredArgsConstructor
public class PushNotificationSender
        implements NotificationSender {

    private final PushProvider pushProvider;

    @Override
    public NotificationChannel supportedChannel() {

        return NotificationChannel.PUSH;
    }

    @Override
    public SendResult send(
            Notification notification
    ) {

        String providerMessageId =
                pushProvider.send(
                        notification.getRecipient(),
                        notification.getSubject(),
                        notification.getMessage()
                );

        return SendResult.success(
                providerMessageId
        );
    }
}