package com.roochi.petflowcommunication.notification.provider.push.impl;

import com.roochi.petflowcommunication.notification.provider.push.PushProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */


@Slf4j
@Component
public class PushProviderImpl implements PushProvider {

    @Override
    public String send(
            String recipient,
            String title,
            String message
    ) {

        log.info(
                "Push provider called. recipient={}, title={}",
                recipient,
                title
        );

        /*
         * TODO:
         * اتصال به Firebase FCM یا Push Provider واقعی
         */

        return UUID.randomUUID().toString();
    }
}
