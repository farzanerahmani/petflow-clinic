package com.roochi.petflowidentity.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RefreshTokenResponseDto")
public class RefreshTokenResponseDto {

    @Schema(description = "${RefreshTokenResponseDto.accessToken}")
    private String accessToken;

    @Schema(description = "${RefreshTokenResponseDto.refreshToken}")
    private String refreshToken;

    @Schema(description = "${RefreshTokenResponseDto.expiresIn}")
    private Long expiresIn;
}
