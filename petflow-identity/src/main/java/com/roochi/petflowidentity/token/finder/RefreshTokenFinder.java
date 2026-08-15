package com.roochi.petflowidentity.token.finder;


import com.roochi.petflowidentity.token.entity.RefreshToken;
import com.roochi.petflowidentity.token.repository.RefreshTokenRepository;
import com.roochi.petflowshared.exception.NotFoundException;
import com.roochi.petflowshared.exception.constants.ErrorCode;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
@RequiredArgsConstructor
public class RefreshTokenFinder {
    private final RefreshTokenRepository repository;

    public RefreshToken findByToken(String token) {
        return repository.findByToken(token).orElseThrow(()->
                new NotFoundException(ErrorCode.TOKEN_NOT_FOUND));
    }
}
