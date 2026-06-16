package com.example.spring_boot_web_socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Configuration class for Cross-Origin Resource Sharing (CORS).
 * <p>
 * This configuration allows frontend applications hosted on specified
 * origins to access the backend APIs. It defines the allowed origins,
 * HTTP methods, headers, and credential-sharing policy for all endpoints.
 * </p>
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     * <p>
     * Applies CORS settings to all endpoints and allows requests from
     * configured frontend domains.
     * </p>
     *
     * @param registry the {@link CorsRegistry} used to register CORS mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applies to all endpoints
                .allowedOrigins(
                        "http://localhost:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}