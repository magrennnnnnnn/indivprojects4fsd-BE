package com.prolink.prolink.controller;

import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import com.prolink.prolink.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public User login(@RequestBody LoginRequest request){
        return authService.login(request.getEmail(),request.getPassword());
    }
}