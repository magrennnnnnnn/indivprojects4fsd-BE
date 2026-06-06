package com.prolink.prolink.config;

import com.prolink.prolink.enums.Roles;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private static final String USER_ID = "userId";
    private static final String USER_EMAIL = "userEmail";
    private static final String ROLES = "roles";

    public void setUserSession(HttpSession session, Long userId, String userEmail, Roles roles) {
        session.setAttribute(USER_ID, userId);
        session.setAttribute(USER_EMAIL, userEmail);
        session.setAttribute(ROLES, roles);
    }

    public Long getUserId(HttpSession session) {
        Object value = session.getAttribute(USER_ID);
        return value instanceof Long ? (Long) value : null;
    }

    public String getUserEmail(HttpSession session) {
        Object value = session.getAttribute(USER_EMAIL);
        return value instanceof String ? (String) value : null;
    }

    public Roles getRoles(HttpSession session) {
        Object value = session.getAttribute(ROLES);
        return value instanceof Roles ? (Roles) value : null;
    }

    public boolean isLoggedIn(HttpSession session) {
        return getUserId(session) != null;
    }

    public void clearSession(HttpSession session) {
        session.invalidate();
    }
}