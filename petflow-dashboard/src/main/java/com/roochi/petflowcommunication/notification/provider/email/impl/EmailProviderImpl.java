package com.roochi.petflowcommunication.notification.provider.email.impl;

import com.roochi.petflowcommunication.notification.provider.email.EmailProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Slf4j
@Component
public class EmailProviderImpl implements EmailProvider {

    @Override
    public String send(
            String recipient,
            String subject,
            String message
    ) {

        log.info(
                "Email provider called. recipient={}, subject={}",
                recipient,
                subject
        );

        /*
         * TODO:
         * اتصال به SMTP یا Email Provider واقعی
         */

        return UUID.randomUUID().toString();
    }
}
