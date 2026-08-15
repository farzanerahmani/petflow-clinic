package com.roochi.petflowidentity.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
@Data
@Schema(name = "RefreshTokenRequestDto")
public class RefreshTokenRequestDto {

    @NotBlank
    @Schema(description = "${RefreshTokenRequestDto.refreshToken}")
    private String refreshToken;
}
