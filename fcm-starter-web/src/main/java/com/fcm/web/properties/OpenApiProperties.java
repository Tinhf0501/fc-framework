package com.fcm.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fcm.open-api")
@Data
public class OpenApiProperties {
    private String title;
    private String version;
    private String description;
    private String tokenUrl;
    private String authorizationUrl;
}
