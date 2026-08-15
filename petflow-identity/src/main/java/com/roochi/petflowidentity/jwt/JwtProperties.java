package com.roochi.petflowidentity.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author farzane.rahmani
 * @created 6/14/2026
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * secret key
     */
    private String secretKey;

    /**
     * milliseconds
     */
    private Long accessTokenExpiration;

    /**
     * milliseconds
     */
    private Long refreshTokenExpiration;
}
