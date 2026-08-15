package com.roochi.petflowidentity.auth.dto.response;

import lombok.*;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private Long expireIn;
}
