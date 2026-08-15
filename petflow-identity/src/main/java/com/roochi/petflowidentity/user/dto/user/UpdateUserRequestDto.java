package com.roochi.petflowidentity.user.dto.user;

import com.roochi.petflowshared.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 6/13/2026
 */
@Schema(name = "UpdateUserRequestDto")
@Data
public class UpdateUserRequestDto {
    @Schema(description = "${UpdateUserRequestDto.id}")
    @NotNull
    private Long id;

    @NotNull
    private UserType userType;

    @NotNull
    @Schema(description = "${UpdateUserRequestDto.active}")
    private Boolean active;

    @NotEmpty
    private Set<Long> rolesIds;

}
