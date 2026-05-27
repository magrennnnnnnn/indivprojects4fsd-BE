package com.prolink.prolink.controller;

import com.prolink.prolink.config.SessionService;
import com.prolink.prolink.dto.ConnectionResponse;
import com.prolink.prolink.dto.SendConnectionRequest;
import com.prolink.prolink.service.ConnectionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/connections")
public class ConnectionController {
    private final ConnectionService connectionService;
    private final SessionService sessionService;

    public ConnectionController(ConnectionService connectionService,SessionService sessionService) {
        this.connectionService = connectionService;
        this.sessionService = sessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConnectionResponse sendConnectionRequest(@Valid @RequestBody SendConnectionRequest request,
                                                    HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return connectionService.sendConnectionRequest(userId, request);
    }

    @PutMapping("/{connectionId}/accept")
    public ConnectionResponse acceptConnectionRequest(@PathVariable Long connectionId,
                                                      HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return connectionService.acceptConnectionRequest(userId, connectionId);
    }

    @PutMapping("/{connectionId}/decline")
    public ConnectionResponse declineConnectionRequest(@PathVariable Long connectionId,
                                                       HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return connectionService.declineConnectionRequest(userId, connectionId);
    }

    @GetMapping("/received")
    public List<ConnectionResponse> getReceivedPendingRequests(HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return connectionService.getReceivedPendingRequests(userId);
    }

    @GetMapping("/sent")
    public List<ConnectionResponse> getSentPendingRequests(HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return connectionService.getSentPendingRequests(userId);
    }

    @GetMapping("/me")
    public List<ConnectionResponse> getMyConnections(HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return connectionService.getMyConnections(userId);
    }
}
