package com.roochi.petflowidentity.user.dto.user;

import com.roochi.petflowshared.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
@Data
@Schema(name = "AddUserRequestDto")
public class AddUserRequestDto {
    @Schema(description = "${AddUserRequestDto.mobile}")
    @NotBlank
    @Pattern(regexp = "^09\\d{9}$")
    private String mobile;

    @NotNull
    private UserType userType;

    /**
     * مثل :
     * CLINIC_ADMIN,
     * DOCTOR,
     * RECEPTIONIST
     */
    @NotEmpty
    @Schema(description = "${AddUserRequestDto.roles}")
    private Set<String> roles;
}
