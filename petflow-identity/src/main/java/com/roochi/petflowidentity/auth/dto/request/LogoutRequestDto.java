package com.roochi.petflowidentity.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Data
@Schema(name = "LogoutRequestDto")
public class LogoutRequestDto {

    @NotBlank
    @Schema(description = "${LogoutRequestDto.refreshToken}")
    private String refreshToken;
}
