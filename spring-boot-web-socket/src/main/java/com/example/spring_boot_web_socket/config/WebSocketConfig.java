package com.example.spring_boot_web_socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for WebSocket messaging using STOMP.
 * <p>
 * This configuration enables a simple in-memory message broker and
 * registers WebSocket endpoints that clients can use to establish
 * real-time communication with the server.
 * </p>
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker used for routing messages between
     * clients and the server.
     * <p>
     * Clients subscribe to destinations prefixed with {@code /topic},
     * and send messages to destinations prefixed with {@code /app}.
     * </p>
     *
     * @param registry the message broker registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");//clients subscribe here
        registry.setApplicationDestinationPrefixes("/app"); //clients send here
    }

    /**
     * Registers WebSocket endpoints that clients use to connect
     * to the WebSocket server.
     * <p>
     * The endpoint {@code /ws} is exposed for WebSocket connections,
     * and requests are allowed from the configured frontend origin.
     * </p>
     *
     * @param registry the STOMP endpoint registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // WebSocket endpoint
                .setAllowedOrigins("http://localhost:5173");
    }
}