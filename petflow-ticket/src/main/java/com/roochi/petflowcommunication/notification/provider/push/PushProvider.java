package com.roochi.petflowcommunication.notification.provider.push;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
public interface PushProvider {

    String send(
            String recipient,
            String title,
            String message
    );
}
