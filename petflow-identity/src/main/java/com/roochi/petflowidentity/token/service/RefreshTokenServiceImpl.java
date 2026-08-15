package com.roochi.petflowidentity.token.service;


import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowshared.security.SecurityUtils;
import com.roochi.petflowidentity.token.entity.RefreshToken;
import com.roochi.petflowidentity.token.finder.RefreshTokenFinder;
import com.roochi.petflowidentity.token.repository.RefreshTokenRepository;
import com.roochi.petflowidentity.token.validator.RefreshTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;
    private final RefreshTokenFinder finder;
    private final RefreshTokenValidator validator;
    private final SecurityUtils securityUtils;

    @Override
    public String create(JwtAuthentication authentication) {
        String value = UUID.randomUUID().toString();
        RefreshToken token = RefreshToken.builder()
                .token(value)
                .userId(authentication.getUserId())
                .userClinicId(authentication.getUserClinicId())
                .clinicId(authentication.getClinicId())
                .expireAt(LocalDateTime.now().plusDays(30))
                .revoked(false).build();
        repository.save(token);
        return value;
    }

    @Override
    public JwtAuthentication validate(String token) {
        RefreshToken refreshToken = finder.findByToken(token);
        validator.validate(refreshToken);
        return JwtAuthentication.builder()
                .userId(refreshToken.getUserId())
                .userClinicId(refreshToken.getUserClinicId())
                .clinicId(refreshToken.getClinicId())
                .authenticated(true)
                .build();
    }

    @Override
    public void revoke(String token) {
        RefreshToken refreshToken = finder.findByToken(token);
        refreshToken.setRevoked(true);
        repository.save(refreshToken);
    }

    @Override
    public void revokeCurrentUser() {
        repository.deleteByUserId(securityUtils.getCurrentUserId());
    }
}
