package com.roochi.petflowidentity.auth.dto.request;

import com.roochi.petflowshared.annotation.Mobile;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendOtpRequestDto {

    @NotBlank
    @Mobile
    private String mobile;
}
