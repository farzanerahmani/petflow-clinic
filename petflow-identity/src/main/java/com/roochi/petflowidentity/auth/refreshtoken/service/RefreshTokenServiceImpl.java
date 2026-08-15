package com.roochi.petflowidentity.auth.refreshtoken.service;

import com.roochi.petflowidentity.jwt.JwtService;
import com.roochi.petflowidentity.auth.refreshtoken.entity.RefreshToken;
import com.roochi.petflowidentity.auth.refreshtoken.repository.RefreshTokenRepository;
import com.roochi.petflowidentity.user.entity.User;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.UnauthorizedException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final JwtService jwtService;
    private final RefreshTokenRepository repository;

    @Override
    public RefreshToken create(User user) {
        String token = jwtService.generateRefreshToken(user.getId());
        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusSeconds(
                        jwtService.getAccessTokenExpiration() / 1000))
                .build();
        return repository.save(refreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken verify(String token) {
        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TOKEN_INVALID));

        if (refreshToken.isRevoked())
            throw new UnauthorizedException(ErrorCode.TOKEN_INVALID);

        if (refreshToken.isExpired())
            throw new UnauthorizedException(ErrorCode.TOKEN_EXPIRED);

        return refreshToken;
    }

    @Override
    public void revoke(String token) {
        RefreshToken refreshToken = verify(token);
        refreshToken.revoke();
        repository.save(refreshToken);
    }

    @Override
    public void revokeAll(Long userId) {
        List<RefreshToken> tokens = repository.findAllByUserIdAndRevokedFalse(userId);
        tokens.forEach(RefreshToken::revoke);
        repository.saveAll(tokens);
    }
}
