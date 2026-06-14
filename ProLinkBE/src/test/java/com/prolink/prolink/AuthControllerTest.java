package com.prolink.prolink;

import com.prolink.prolink.config.SessionService;
import com.prolink.prolink.controller.AuthController;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.enums.Roles;
import com.prolink.prolink.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private SessionService sessionService;

    @Test
    void register_ShouldReturnCreatedUser() throws Exception {
        User user = new User(
                1L,
                "test@test.com",
                "password123",
                Roles.STANDARD_USER
        );

        when(authService.register(
                eq("test@test.com"),
                eq("password123"),
                eq(Roles.STANDARD_USER)
        )).thenReturn(user);

        String body = """
                {
                  "email": "test@test.com",
                  "password": "password123",
                  "roles": "STANDARD_USER"
                }
                """;

        mockMvc.perform(post("/auth/register")
                        .contentType(APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.roles").value("STANDARD_USER"));

        verify(authService).register(
                "test@test.com",
                "password123",
                Roles.STANDARD_USER
        );
    }

    @Test
    void login_ShouldCreateSessionAndReturnUser() throws Exception {
        User user = new User(
                1L,
                "test@test.com",
                "password123",
                Roles.STANDARD_USER
        );

        when(authService.login(
                eq("test@test.com"),
                eq("password123")
        )).thenReturn(user);

        String body = """
                {
                  "email": "test@test.com",
                  "password": "password123"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.roles").value("STANDARD_USER"));

        verify(authService).login("test@test.com", "password123");

        verify(sessionService).setUserSession(
                any(HttpSession.class),
                eq(1L),
                eq("test@test.com"),
                eq(Roles.STANDARD_USER)
        );
    }
}