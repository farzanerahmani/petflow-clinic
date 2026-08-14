package com.roochi.petflowcommunication.notification.channel.sms;

import com.roochi.petflowcommunication.notification.channel.NotificationSender;
import com.roochi.petflowcommunication.notification.channel.SendResult;
import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowcommunication.notification.provider.sms.SmsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Component
@RequiredArgsConstructor
public class SmsNotificationSender implements NotificationSender {

    private final SmsProvider smsProvider;

    @Override
    public NotificationChannel supportedChannel() {

        return NotificationChannel.SMS;
    }

    @Override
    public SendResult send(
            Notification notification
    ) {

        String providerMessageId =
                smsProvider.send(
                        notification.getRecipient(),
                        notification.getMessage()
                );

        return SendResult.success(
                providerMessageId
        );
    }
}