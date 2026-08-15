package com.roochi.petflowidentity.user.dto.role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Getter
@Setter
public class AssignRoleRequestDto {

    @NotNull
    private Long userId;

    @NotEmpty
    private Set<Long> roleIds;
}
