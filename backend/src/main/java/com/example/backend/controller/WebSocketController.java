package com.example.backend.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Controller
@EnableWebSocketMessageBroker
public class WebSocketController implements WebSocketMessageBrokerConfigurer{

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketController(@Lazy SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/logs");
    }

    public void sendLogs(String logMessage) {
        simpMessagingTemplate.convertAndSend("/topic/logs", logMessage);
        //simpMessagingTemplate.convertAndSend();
    }
    


}
