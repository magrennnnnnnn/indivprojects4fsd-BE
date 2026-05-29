package com.prolink.prolink.controller;

import com.prolink.prolink.dto.SendMessageRequest;
import com.prolink.prolink.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class MessageWebSocketController  {
    private final MessageService messageService;

    public MessageWebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/messages.send")
    public void sendMessage(SendMessageRequest request,
                            SimpMessageHeaderAccessor headerAccessor) {
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

        if (sessionAttributes == null || sessionAttributes.get("userId") == null) {
            throw new RuntimeException("Not logged in");
        }

        Long userId = Long.valueOf(sessionAttributes.get("userId").toString());

        messageService.sendMessage(userId, request);
    }
}
