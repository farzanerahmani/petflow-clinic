package com.roochi.petflowidentity.auth.controller;

import com.roochi.petflowidentity.auth.dto.request.*;
import com.roochi.petflowidentity.auth.dto.response.LoginResponseDto;
import com.roochi.petflowidentity.auth.dto.response.RefreshTokenResponseDto;
import com.roochi.petflowidentity.auth.facade.AuthFacade;
import com.roochi.petflowidentity.auth.service.command.AuthCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthFacade {

    private final AuthCommandService authCommandService;

    @Override
    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto requestDto) {
        return authCommandService.login(requestDto);
    }

    @Override
    @PostMapping("refresh")
    public RefreshTokenResponseDto refresh(@Valid @RequestBody RefreshTokenRequestDto requestDto) {
        return authCommandService.refreshToken(requestDto);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/logout")
    public void logout(@Valid @RequestBody LogoutRequestDto requestDto) {

        authCommandService.logout(requestDto);
    }
}
