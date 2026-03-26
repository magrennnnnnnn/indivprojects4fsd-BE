package com.prolink.prolink.repository;

import com.prolink.prolink.domain.UserD;

import java.util.Optional;

public interface UserRepository {
    Optional<UserD> findByEmail(String email);
    UserD save(UserD user);
}
