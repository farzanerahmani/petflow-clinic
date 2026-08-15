package com.roochi.petflowidentity.jwt;

import com.roochi.petflowshared.security.JwtAuthentication;
import com.roochi.petflowshared.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public Long getUserIdFromTemporaryToken(String token) {
        Claims claims =
                Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJwt(token)
                        .getBody();
        return Long.valueOf(claims.getSubject());
    }

    @Override
    public boolean validateTemporaryToken(String token) {
        try {
            Claims claims =
                    Jwts.parserBuilder()
                            .setSigningKey(getSigningKey())
                            .build()
                            .parseClaimsJwt(token)
                            .getBody();
            return "TEMP".equals(claims.get(JwtClaims.TYPE));
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String generateTemporaryToken(Long userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 5 * 60 * 1000);
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim(JwtClaims.TYPE, "TEMP")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSigningKey())
                .compact();

    }

    @Override
    public String generateAccessToken(JwtAuthentication authentication) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getAccessTokenExpiration());

        Map<String, Object> claims = Map.of(
                JwtClaims.USER_ID, authentication.getUserId(),
                JwtClaims.USER_CLINIC_ID, authentication.getUserClinicId(),
                JwtClaims.CLINIC_ID, authentication.getClinicId(),
                JwtClaims.USER_TYPE, authentication.getUserType().getTitle(),
                JwtClaims.ROLES, authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList());


        return Jwts.builder().setSubject(authentication.getMobile())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(),
                        SignatureAlgorithm.HS512).compact();
    }


    @Override
    public String generateRefreshToken(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getRefreshTokenExpiration());

        return Jwts.builder().claim(JwtClaims.USER_ID, userId).setIssuedAt(now)
                .setExpiration(expiration).signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String extractSubject(String token) {
        return extractClaims(token).getSubject();
    }

    @Override
    public Long extractUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return extractExpiration(token).before(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            return true;
        }
    }

    @Override
    public Long getAccessTokenExpiration() {
        return jwtProperties.getAccessTokenExpiration();
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()).build().parseClaimsJwt(token).getBody();
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    @Override
    public Long getRefreshTokenExpiration() {
        return jwtProperties.getRefreshTokenExpiration();
    }


    @Override
    public Long extractClinicId(String token) {
        return extractClaims(token).get(JwtClaims.CLINIC_ID, Long.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return extractClaims(token).get("roles", List.class);
    }

    @Override
    public JwtAuthentication parseAuthentication(String token) {
        Claims claims = extractClaims(token);

        Long userId = claims.get(JwtClaims.USER_ID, Long.class);
        Long userClinicId = claims.get(JwtClaims.USER_CLINIC_ID, Long.class);
        Long clinicId = claims.get(JwtClaims.CLINIC_ID, Long.class);
        String mobile = claims.getSubject();
        UserType userType = UserType.valueOf(claims.get(JwtClaims.USER_TYPE, String.class));
        List<String> roles = claims.get(JwtClaims.ROLES, List.class);
        List<GrantedAuthority> authorities =
                roles.stream().map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        //todo:
        //add tenet type claim to jwt when tenant type are introduced.

        return JwtAuthentication.builder()
                .userId(userId)
                .userClinicId(userClinicId)
                .clinicId(clinicId)
                .mobile(mobile)
                .userType(userType)
                .roles(new HashSet<>(roles))
                .authorities(authorities)
                .authenticated(true).build();
    }

    private String buildToken(Map<String, Object> claims, String subject, Long expiration) {
        return Jwts.builder()
                .setClaims(claims).
                setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
