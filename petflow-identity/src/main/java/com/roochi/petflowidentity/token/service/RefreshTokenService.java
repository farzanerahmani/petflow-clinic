package com.roochi.petflowidentity.token.service;

import com.roochi.petflowshared.security.JwtAuthentication;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
public interface RefreshTokenService {
    String create(JwtAuthentication authentication);
    JwtAuthentication validate(String token);
    void revoke(String token);
    void revokeCurrentUser();
}
