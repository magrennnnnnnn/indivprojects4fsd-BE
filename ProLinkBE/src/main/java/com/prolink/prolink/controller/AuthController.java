package com.prolink.prolink.controller;

import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.RegisterRequest;
import com.prolink.prolink.dto.UserResponse;
import org.springframework.http.HttpStatus;
import com.prolink.prolink.service.AuthService;
import com.prolink.prolink.dto.LoginRequest;
import com.prolink.prolink.config.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;

    public AuthController(AuthService authService,SessionService sessionService) {
        this.authService = authService;
        this.sessionService=sessionService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody RegisterRequest request){
        User user = authService.register(request.getEmail(), request.getPassword());
        return new UserResponse(user.getId(), user.getEmail());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody LoginRequest request,HttpSession session){
        User user = authService.login(request.getEmail(), request.getPassword());
        sessionService.setUserSession(session, user.getId(), user.getEmail());

        return new UserResponse(user.getId(), user.getEmail());
    }

    @GetMapping("/me")
    public UserResponse me(HttpSession session) {
        if (!sessionService.isLoggedIn(session)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return new UserResponse(
                sessionService.getUserId(session),
                sessionService.getUserEmail(session)
        );
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout(HttpSession session) {
        sessionService.clearSession(session);
        return "Logged out successfully";
    }
}