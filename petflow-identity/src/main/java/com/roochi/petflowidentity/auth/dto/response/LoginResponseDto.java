package com.roochi.petflowidentity.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "LoginResponseDto")
public class LoginResponseDto {

    private boolean needSetPin;
    private boolean needSelectClinic;
    private String accessToken;
    private String refreshToken;
    private String temporaryToken;

//    private boolean requiresTenantSelection;
//    private List<TenantSelectResponseDto> tenants;
//    private AuthTokenResponseDto token;
//    private LoginNextAction nextAction;
//
//    @Schema(description = "${LoginResponseDto.userId}")
//    private Long userId;
//
//    @Schema(description = "${LoginResponseDto.mobile}")
//    private String mobile;
//
//    @Schema(description = "${LoginResponseDto.fullName}")
//    private String fullName;
//
//    @Schema(description = "${LoginResponseDto.accessToken}")
//    private String accessToken;
//
//    @Schema(description = "${LoginResponseDto.refreshToken}")
//    private String refreshToken;
//
//    @Schema(description = "${LoginResponseDto.expiresIn}")
//    private Long expiresIn;
}
