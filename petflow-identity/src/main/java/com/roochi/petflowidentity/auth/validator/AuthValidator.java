package com.roochi.petflowidentity.auth.validator;

import com.roochi.petflowidentity.auth.dto.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class AuthValidator {

    public void validate(LoginRequestDto requestDto){
        if(!StringUtils.hasText(requestDto.getMobile())){
            throw new IllegalArgumentException("Mobile is required");
        }
        if(!StringUtils.hasText(requestDto.getPassword())){
            throw new IllegalArgumentException("Password is required");
        }
    }

    public void validate(SelectClinicRequestDto requestDto){
        if(requestDto.getUserClinicId()==null){
            throw new IllegalArgumentException("User clinic is required");
        }
    }

    public void validate(RefreshTokenRequestDto requestDto){
        if(!StringUtils.hasText(requestDto.getRefreshToken())){
            throw new IllegalArgumentException("Refresh token is required");
        }
    }

    public void validate(SendOtpRequestDto requestDto){

    }

    public void validate(VerifyOtpRequestDto requestDto){}
}
