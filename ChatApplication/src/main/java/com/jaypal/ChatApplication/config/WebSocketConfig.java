package com.jaypal.ChatApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Enables STOMP/WebSocket messaging handling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * This method registers the endpoint that the clients will use to connect to the WebSocket server.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws") // Endpoint clients connect to (e.g. SockJS('/ws'))
                .withSockJS(); // Enable fallback options for browsers that don’t support WebSocket
    }

    /**
     * This method configures message routing:
     * - Application prefix routes go to message-handling methods (@MessageMapping)
     * - Broker prefix routes are sent to subscribers
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for client-to-server messages
        registry.enableSimpleBroker("/topic"); // Prefix for server-to-client broadcasts
    }
}
