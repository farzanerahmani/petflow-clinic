package com.roochi.petflowidentity.jwt;

import com.roochi.petflowshared.security.JwtAuthentication;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
public interface JwtService {
    Long getUserIdFromTemporaryToken(String token);

    boolean validateTemporaryToken(String token);
    String generateTemporaryToken(Long userId);

    String generateAccessToken(JwtAuthentication authentication);

    String generateRefreshToken(Long userId);

    boolean isTokenValid(String token);

    Claims extractClaims(String token);

    String extractSubject(String token);

    Date extractExpiration(String token);

    JwtAuthentication parseAuthentication(String token);
    //////

    Long extractUserId(String token);

    Long extractClinicId(String token);

    List<String> extractRoles(String token);


    Long getAccessTokenExpiration();


    Long getRefreshTokenExpiration();


}
