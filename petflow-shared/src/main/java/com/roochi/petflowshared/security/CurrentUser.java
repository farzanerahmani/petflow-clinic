package com.roochi.petflowshared.security;

import com.roochi.petflowshared.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Getter
@AllArgsConstructor
public class CurrentUser {
    private final Long id;
    private final Long userClinicId;
    private final String mobile;
    private final UserType userType;
    private final Set<String> roles;
}
