package com.awd.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Partie 2 - Méthode 1 : Configuration statique des routes via Java (RouteLocator).
 * Activer avec : --spring.profiles.active=static-java
 */
@Configuration
@Profile("static-java")
public class StaticRoutesConfig {

    @Bean
    public RouteLocator staticGatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("candidat-static", r -> r.path("/candidates/**", "/mic1/addresses/**")
                        .uri("http://localhost:8081"))
                .route("job-static", r -> r.path("/api/jobs/**", "/api/categories/**")
                        .uri("http://localhost:8082"))
                .route("meeting-static", r -> r.path("/api/meetings/**")
                        .uri("http://localhost:8083"))
                .route("notification-static", r -> r.path("/api/notifications/**")
                        .uri("http://localhost:8084"))
                .route("canditature-static", r -> r.path("/candidature/**")
                        .uri("http://localhost:8085"))
                .build();
    }
}
