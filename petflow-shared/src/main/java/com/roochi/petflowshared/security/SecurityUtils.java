package com.roochi.petflowshared.security;

import com.roochi.petflowshared.context.RequestContext;
import com.roochi.petflowshared.context.RequestContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Component
public class SecurityUtils {

    public JwtAuthentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("No authentication found in security context");
        }
        if (!(authentication.getPrincipal() instanceof JwtAuthentication jwtAuth)) {
            throw new IllegalStateException("Invalid authentication principal type");
        }
        return jwtAuth;
    }

    public CurrentUser getCurrentUser() {
        JwtAuthentication authentication = getAuthentication();
        if (authentication == null) return null;
        return new CurrentUser(authentication.getUserId(),
                authentication.getUserClinicId(),
                authentication.getMobile(),
                authentication.getUserType(),
                authentication.getRoles());
    }

    public CurrentClinic getCurrentClinic() {
        JwtAuthentication authentication = getAuthentication();
        if (authentication == null) return null;
        return new CurrentClinic(authentication.getClinicId(), authentication.getClinicType());
    }

    public Long getCurrentUserId() {
        CurrentUser currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getId();
    }

    public Long getCurrentClinicId() {
        CurrentClinic currentClinic = getCurrentClinic();
        return currentClinic == null ? null : currentClinic.getId();
    }

    public Long getCurrentUserClinicId(){
        CurrentUser currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getUserClinicId();
    }

    public RequestContext getRequestContext(){
        return RequestContextHolder.get();
    }
}
