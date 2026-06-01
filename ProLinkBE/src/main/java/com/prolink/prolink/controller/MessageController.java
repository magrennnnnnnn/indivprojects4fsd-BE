package com.prolink.prolink.controller;

import com.prolink.prolink.config.SessionService;
import com.prolink.prolink.dto.MessageResponse;
import com.prolink.prolink.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    private final SessionService sessionService;

    public MessageController(MessageService messageService,
                             SessionService sessionService) {
        this.messageService = messageService;
        this.sessionService = sessionService;
    }

    @GetMapping("/{otherProfileId}")
    public List<MessageResponse> getMessagesWithProfile(@PathVariable Long otherProfileId,
                                                        HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return messageService.getMessagesWithProfile(userId, otherProfileId);
    }
}
