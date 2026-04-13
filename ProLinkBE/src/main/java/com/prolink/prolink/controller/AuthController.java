package com.prolink.prolink.controller;

import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.RegisterRequest;
import com.prolink.prolink.dto.UserResponse;
import org.springframework.http.HttpStatus;
import com.prolink.prolink.service.AuthService;
import com.prolink.prolink.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegisterRequest request){
        return authService.register(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody LoginRequest request){
        User user = authService.login(request.getEmail(), request.getPassword());
        return new UserResponse(user.getId(), user.getEmail());
    }
}