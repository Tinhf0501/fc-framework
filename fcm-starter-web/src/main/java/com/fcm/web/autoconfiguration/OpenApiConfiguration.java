package com.fcm.web.autoconfiguration;

import com.fcm.web.properties.OpenApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(OpenApiProperties.class)
public class OpenApiConfiguration {

    @Autowired
    private OpenApiProperties openApiProperties;

    @Bean
    public OpenAPI openAPI() {
        OAuthFlow oAuthFlow = new OAuthFlow()
                .tokenUrl(this.openApiProperties.getTokenUrl())
                .authorizationUrl(this.openApiProperties.getAuthorizationUrl());
        return new OpenAPI().info(
            new Info()
                .title(this.openApiProperties.getTitle())
                .version(this.openApiProperties.getVersion())
                .description(this.openApiProperties.getDescription()))
            .components(new Components()
                    .addSecuritySchemes("FCM Auth Service",
                        new SecurityScheme()
                            .name("FCM Auth Service")
                            .type(SecurityScheme.Type.OAUTH2)
                            .scheme("oauth2")
                            .flows(new OAuthFlows().authorizationCode(oAuthFlow))
                    ));
    }
}
