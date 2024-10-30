package com.fcm.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "fcm.logging")
@Data
public class LoggingProperties {
    private Set<String> ignoresPath = new HashSet<>();
}