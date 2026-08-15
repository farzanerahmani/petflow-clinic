package com.roochi.petflowidentity.auth.dto.request;

import com.roochi.petflowshared.annotation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author farzane.rahmani
 * @created 7/2/2026
 */
@Data
@Schema(name = "LoginRequestDto")
public class LoginRequestDto {

    @Schema(description = "${LoginRequestDto.mobile}")
    @NotBlank(message = "Mobile is required.")
    @Mobile
    private String mobile;

    @Schema(description = "${LoginRequestDto.credential}")
    @NotBlank(message = "Credential is required.")
    private String password;

}
