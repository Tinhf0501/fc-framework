package com.fcm.data.jpa.autoconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class FcmDataJpaConfiguration {

    @Bean("auditAwareImpl")
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("FC_MANAGEMENT");
    }
}
