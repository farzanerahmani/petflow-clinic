package com.roochi.petflowidentity.otp.generator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
public class RandomOtpGenerator implements OtpGenerator {
    private static final SecureRandom RANDOM =new SecureRandom();

    @Override
    public String generate() {
        int value = 100000+
                RANDOM.nextInt(900000);
        return String.valueOf(value);
    }
}
