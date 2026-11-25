package com.awd.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Partie 3 - Méthode 1 : Configuration dynamique des routes via Java (RouteLocator + lb://).
 * Activer avec : --spring.profiles.active=dynamic-java
 */
@Configuration
@Profile("dynamic-java")
public class DynamicRoutesConfig {

    @Bean
    public RouteLocator dynamicGatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("candidat-dynamic", r -> r.path("/candidates/**", "/mic1/addresses/**")
                        .uri("lb://candidat"))
                .route("job-dynamic", r -> r.path("/api/jobs/**", "/api/categories/**")
                        .uri("lb://job"))
                .route("meeting-dynamic", r -> r.path("/api/meetings/**")
                        .uri("lb://meeting"))
                .route("notification-dynamic", r -> r.path("/api/notifications/**")
                        .uri("lb://notification"))
                .route("canditature-dynamic", r -> r.path("/candidature/**")
                        .uri("lb://canditature"))
                .build();
    }
}
