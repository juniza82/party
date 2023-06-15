package com.juniza82.party.component.jpa;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Autowired
            private HttpServletRequest httpServletRequest;

            @Override
            public Optional<String> getCurrentAuditor() {
                String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

                assert (header != null);

                return Optional.of(header);
            }
        };}
}
