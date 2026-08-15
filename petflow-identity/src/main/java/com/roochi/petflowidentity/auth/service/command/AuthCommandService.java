package com.roochi.petflowidentity.auth.service.command;

import com.roochi.petflowidentity.auth.dto.request.SendOtpRequestDto;
import com.roochi.petflowidentity.auth.dto.request.VerifyOtpRequestDto;
import com.roochi.petflowidentity.auth.dto.request.*;
import com.roochi.petflowidentity.auth.dto.response.*;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
public interface AuthCommandService {

    SendOtpResponsetDto sendOtp(SendOtpRequestDto requestDto);

    LoginResponseDto verifyOtp(VerifyOtpRequestDto requestDto);

    LoginResponseDto setPin(SetPinRequestDto requestDto);

    LoginResponseDto selectClinic(SelectClinicRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto requestDto);

    LogoutResponseDto logout(LogoutRequestDto requestDto);
}
