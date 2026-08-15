package com.roochi.petflowcommunication.notification.channel;

import com.roochi.petflowcommunication.notification.entity.Notification;
import com.roochi.petflowcommunication.notification.entity.enums.NotificationChannel;


/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface NotificationSender {

    NotificationChannel supportedChannel();

    SendResult send(Notification notification);
}
