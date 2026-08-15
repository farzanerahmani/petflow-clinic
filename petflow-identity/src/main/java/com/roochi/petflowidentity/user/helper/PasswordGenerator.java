package com.roochi.petflowidentity.user.helper;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author farzane.rahmani
 * @created 7/7/2026
 */
@Component
public class PasswordGenerator {

    public static String generate(){
        return UUID.randomUUID()
                .toString()
                .replace("-","")
                .substring(0,8);
    }
}
