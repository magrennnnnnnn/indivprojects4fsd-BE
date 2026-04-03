package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Profile;

import java.util.Optional;

public interface ProfileRepository {
    Optional<Profile> findByIdProfile(Long idProfile);
    Optional<Profile> findByUserId(Long userId);
    Profile save(Profile profile);
}