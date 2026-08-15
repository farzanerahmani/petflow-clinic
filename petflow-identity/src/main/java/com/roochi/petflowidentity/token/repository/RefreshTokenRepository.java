package com.roochi.petflowidentity.token.repository;



import com.roochi.petflowidentity.token.entity.RefreshToken;
import com.roochi.petflowshared.repository.BaseRepository;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface RefreshTokenRepository extends BaseRepository<RefreshToken, Long> {


    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(Long userId);
}
