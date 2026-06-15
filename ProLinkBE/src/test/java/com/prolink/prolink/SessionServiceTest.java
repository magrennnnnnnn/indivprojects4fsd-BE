package com.prolink.prolink;

import com.prolink.prolink.config.SessionService;
import com.prolink.prolink.enums.Roles;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceTest {

    private final SessionService sessionService = new SessionService();

    @Test
    void setUserSession_ShouldStoreUserDataInSession() {
        HttpSession session = new MockHttpSession();

        sessionService.setUserSession(
                session,
                1L,
                "test@test.com",
                Roles.STANDARD_USER
        );

        assertEquals(1L, sessionService.getUserId(session));
        assertEquals("test@test.com", sessionService.getUserEmail(session));
        assertEquals(Roles.STANDARD_USER, sessionService.getRoles(session));
    }

    @Test
    void getUserId_ShouldReturnNull_WhenUserIdIsMissing() {
        HttpSession session = new MockHttpSession();

        Long result = sessionService.getUserId(session);

        assertNull(result);
    }

    @Test
    void getUserEmail_ShouldReturnNull_WhenEmailIsMissing() {
        HttpSession session = new MockHttpSession();

        String result = sessionService.getUserEmail(session);

        assertNull(result);
    }

    @Test
    void getRoles_ShouldReturnNull_WhenRoleIsMissing() {
        HttpSession session = new MockHttpSession();

        Roles result = sessionService.getRoles(session);

        assertNull(result);
    }

    @Test
    void clearSession_ShouldInvalidateSession() {
        MockHttpSession session = new MockHttpSession();

        sessionService.setUserSession(
                session,
                1L,
                "test@test.com",
                Roles.PREMIUM_USER
        );

        sessionService.clearSession(session);

        assertTrue(session.isInvalid());
    }
}