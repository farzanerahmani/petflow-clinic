package com.roochi.petflowcommunication.notification.channel.impl;

import com.roochi.petflowcommunication.notification.channel.NotificationSender;
import com.roochi.petflowcommunication.notification.channel.NotificationSenderDispatcher;
import com.roochi.petflowcommunication.notification.channel.SendResult;
import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */

@Component
@RequiredArgsConstructor
public class NotificationSenderDispatcherImpl
        implements NotificationSenderDispatcher {

    private final List<NotificationSender> senders;

    private Map<NotificationChannel, NotificationSender> senderMap;


    @jakarta.annotation.PostConstruct
    public void init() {

        senderMap =
                new EnumMap<>(
                        NotificationChannel.class
                );

        senders.forEach(sender ->
                senderMap.put(
                        sender.supportedChannel(),
                        sender
                )
        );
    }


    @Override
    public SendResult dispatch(
            Notification notification
    ) {

        NotificationSender sender =
                senderMap.get(
                        notification.getChannel()
                );

        if (sender == null) {

            return SendResult.failure(
                    "No notification sender found for channel: "
                            + notification.getChannel()
            );
        }

        return sender.send(notification);
    }
}