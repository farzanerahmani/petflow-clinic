package com.roochi.petflowshared.security;


import com.roochi.petflowshared.enums.ClinicType;
import com.roochi.petflowshared.enums.UserType;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthentication implements Authentication {
    private Long userId;
    private Long userClinicId;
    private Long clinicId;
    private ClinicType clinicType;
    private Set<String> roles;
    private String mobile;
    private UserType userType;
    private Collection<GrantedAuthority> authorities;
    private boolean authenticated = true;

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return mobile;
    }
}
