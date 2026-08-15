package com.roochi.petflowidentity.user.dto.user;

import com.roochi.petflowshared.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 6/30/2026
 */
@Getter
@Setter
public class UserDto {
    private Long id;
    private Boolean active;
    private String mobile;
    private UserType userType;
    private Set<RoleDto> roles;
}
