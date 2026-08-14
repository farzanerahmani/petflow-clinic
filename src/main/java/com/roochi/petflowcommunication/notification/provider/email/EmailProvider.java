package com.roochi.petflowcommunication.notification.provider.email;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface EmailProvider {

    String send(
            String recipient,
            String subject,
            String message
    );
}
