package com.roochi.petflowidentity.auth.dto.request;

import com.roochi.petflowshared.annotation.Mobile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Getter
@Setter
public class VerifyOtpRequestDto {

    @NotBlank
    @Mobile
    private String mobile;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$")
    private String code;
}
