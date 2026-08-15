package com.roochi.petflowcommunication.notification.provider.sms;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface SmsProvider {

    String send(
            String recipient,
            String message
    );
}
