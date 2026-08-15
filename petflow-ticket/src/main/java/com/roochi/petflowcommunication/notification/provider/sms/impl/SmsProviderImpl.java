package com.roochi.petflowcommunication.notification.provider.sms.impl;

import com.roochi.petflowcommunication.notification.provider.sms.SmsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Slf4j
@Component
public class SmsProviderImpl implements SmsProvider {

    @Override
    public String send(
            String recipient,
            String message
    ) {

        log.info(
                "SMS provider called. recipient={}, message={}",
                recipient,
                message
        );

        /*
         * TODO:
         * اتصال به سرویس واقعی SMS
         */

        return UUID.randomUUID().toString();
    }
}
