package com.roochi.petflowshared.config;

import com.roochi.petflowshared.security.SpringSecurityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author farzane.rahmani
 * @created 7/4/2026
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef ="auditorAware")
public class JpaAuditConfig {

    @Bean
    public AuditorAware<Long> auditorAware(){
     return new SpringSecurityAuditorAware();
    }
}
