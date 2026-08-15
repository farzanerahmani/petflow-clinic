package com.roochi.petflowidentity.otp.validator;

import com.roochi.petflowidentity.otp.entity.Otp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
public class OtpValidator{
    public void  validateCanSend(Otp otp){
        if(otp.getCreatedAt().plusSeconds(60).isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("");
    }

    public void validateVerification(Otp otp){
        if(otp.isUsed())
            throw new IllegalArgumentException();
        if(otp.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException();
    }
}
