package com.fcm.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "fcm.security")
@Setter
@Getter
public class SecurityProperties {
    private Set<String> publicUrl = new HashSet<>();
}
