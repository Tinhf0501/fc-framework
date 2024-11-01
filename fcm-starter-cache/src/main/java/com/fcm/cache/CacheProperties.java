package com.fcm.cache;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix ="fcm.cache")
@Setter
@Getter
public class CacheProperties {

    private Map<String, CacheWrapper> cache = new HashMap<>();

    @Data
    public static class CacheWrapper {
        private Duration ttl;
    }
}
