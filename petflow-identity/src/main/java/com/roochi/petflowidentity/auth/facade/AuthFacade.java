package com.roochi.petflowidentity.auth.facade;

import com.roochi.petflowidentity.auth.dto.request.*;
import com.roochi.petflowidentity.auth.dto.response.LoginResponseDto;
import com.roochi.petflowidentity.auth.dto.response.RefreshTokenResponseDto;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
public interface AuthFacade {

    LoginResponseDto login(LoginRequestDto requestDto);
    RefreshTokenResponseDto refresh(RefreshTokenRequestDto requestDto);
    void logout(LogoutRequestDto requestDto);
}
