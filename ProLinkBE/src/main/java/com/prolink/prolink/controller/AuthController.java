package com.prolink.prolink.controller;

import com.prolink.prolink.dto.RegisterRequest;
import com.prolink.prolink.entity.User;
import com.prolink.prolink.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request.getEmail(), request.getPassword());
    }
}