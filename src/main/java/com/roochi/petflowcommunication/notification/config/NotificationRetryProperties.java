package com.roochi.petflowcommunication.notification.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */
@Component
@ConfigurationProperties(prefix = "communication.notification.retry")
@Getter
@Setter
public class NotificationRetryProperties {

    private int maxAttempts = 3;

    private long firstDelaySeconds = 30;

    private long secondDelaySeconds = 120;
}
