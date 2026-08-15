package com.roochi.petflowaudit.aop;

import com.roochi.petflowshared.security.JwtAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author farzane.rahmani
 * @created 8/11/2026
 */

@Component
@RequiredArgsConstructor
public class AuditContext {

    public Long getUserId() {

        JwtAuthentication authentication =
                getJwtAuthentication();

        if (authentication == null) {
            return null;
        }

        return authentication.getUserId();
    }

    public Long getClinicId() {

        JwtAuthentication authentication =
                getJwtAuthentication();

        if (authentication == null) {
            return null;
        }

        return authentication.getClinicId();
    }

    public String getIpAddress() {

        HttpServletRequest request =
                getCurrentRequest();

        if (request == null) {
            return null;
        }

        String forwardedFor =
                request.getHeader("X-Forwarded-For");

        if (forwardedFor != null
                && !forwardedFor.isBlank()) {

            return forwardedFor
                    .split(",")[0]
                    .trim();
        }

        return request.getRemoteAddr();
    }

    public String getUserAgent() {

        HttpServletRequest request =
                getCurrentRequest();

        if (request == null) {
            return null;
        }

        return request.getHeader("User-Agent");
    }

    private JwtAuthentication getJwtAuthentication() {

        Object authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication instanceof JwtAuthentication jwt) {
            return jwt;
        }

        return null;
    }

    private HttpServletRequest getCurrentRequest() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();

        if (attributes == null) {
            return null;
        }

        return attributes.getRequest();
    }
}