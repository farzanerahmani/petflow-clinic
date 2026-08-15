package com.roochi.petflowidentity.token.validator;


import com.roochi.petflowidentity.token.entity.RefreshToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
public class RefreshTokenValidator {

    public void validate(RefreshToken refreshToken){
        if(refreshToken.isRevoked())
            throw new IllegalArgumentException("");
        if(refreshToken.getExpireAt().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("");
    }
}
