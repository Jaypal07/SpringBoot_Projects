package com.jaypal.ChatApplication.config;

import com.jaypal.ChatApplication.entity.ChatMessage;
import com.jaypal.ChatApplication.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    /**
     * Handles WebSocket disconnect events.
     * This method is triggered when a client disconnects from the WebSocket session.
     */
    @EventListener // <---- This is crucial
    public void handleWebSocketDisconnectionListener(SessionDisconnectEvent disconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            log.info("User disconnected: {}", username);

            var chatMessage = ChatMessage.builder() // type change from ChatMessage to var
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();

            // Notify all connected clients that this user has left
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
