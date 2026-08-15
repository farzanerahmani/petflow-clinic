package com.roochi.petflowshared.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Component
@RequiredArgsConstructor
public class SpringSecurityAuditorAware implements AuditorAware<Long> {
    private final SecurityUtils securityUtils;

    @Override
    public Optional<Long> getCurrentAuditor() {
        JwtAuthentication authentication =
                securityUtils.getAuthentication();
        if(authentication==null)
            return Optional.empty();;

        return Optional.of(authentication.getUserId());
    }
}
