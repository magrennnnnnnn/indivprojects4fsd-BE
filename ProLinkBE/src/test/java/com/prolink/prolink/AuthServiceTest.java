package com.prolink.prolink;

import com.prolink.prolink.domain.User;
import com.prolink.prolink.enums.Roles;
import com.prolink.prolink.exceptionhandler.EmailAlreadyExistsException;
import com.prolink.prolink.exceptionhandler.PasswordIsIncorrectException;
import com.prolink.prolink.repository.UserRepository;
import com.prolink.prolink.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_ShouldCreateUser_WhenEmailDoesNotExist() {
        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        User savedUser = new User("test@test.com", "password123", Roles.STANDARD_USER);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        User result = authService.register("test@test.com", "password123", Roles.STANDARD_USER);

        assertEquals("test@test.com", result.getEmail());
        assertEquals("password123", result.getPassword());
        assertEquals(Roles.STANDARD_USER, result.getRoles());

        verify(userRepository).findByEmail("test@test.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ShouldUseStandardUser_WhenRoleIsNull() {
        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        User savedUser = new User("test@test.com", "password123", Roles.STANDARD_USER);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        User result = authService.register("test@test.com", "password123", null);

        assertEquals("test@test.com", result.getEmail());
        assertEquals(Roles.STANDARD_USER, result.getRoles());

        verify(userRepository).findByEmail("test@test.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        User existingUser = new User("test@test.com", "password123", Roles.STANDARD_USER);

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(existingUser));

        assertThrows(
                EmailAlreadyExistsException.class,
                () -> authService.register("test@test.com", "password123", Roles.STANDARD_USER)
        );

        verify(userRepository).findByEmail("test@test.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenRoleIsAdmin() {
        when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> authService.register("admin@test.com", "password123", Roles.ADMIN)
        );

        verify(userRepository).findByEmail("admin@test.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_ShouldReturnUser_WhenCredentialsAreValid() {
        User existingUser = new User("test@test.com", "password123", Roles.STANDARD_USER);

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(existingUser));

        User result = authService.login("test@test.com", "password123");

        assertEquals("test@test.com", result.getEmail());
        assertEquals("password123", result.getPassword());

        verify(userRepository).findByEmail("test@test.com");
    }

    @Test
    void login_ShouldThrowException_WhenPasswordIsWrong() {
        User existingUser = new User("test@test.com", "password123", Roles.STANDARD_USER);

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(existingUser));

        assertThrows(
                PasswordIsIncorrectException.class,
                () -> authService.login("test@test.com", "wrongpassword")
        );

        verify(userRepository).findByEmail("test@test.com");
    }

    @Test
    void login_ShouldThrowException_WhenEmailDoesNotExist() {
        when(userRepository.findByEmail("missing@test.com"))
                .thenReturn(Optional.empty());

        assertThrows(
                PasswordIsIncorrectException.class,
                () -> authService.login("missing@test.com", "password123")
        );

        verify(userRepository).findByEmail("missing@test.com");
    }
}