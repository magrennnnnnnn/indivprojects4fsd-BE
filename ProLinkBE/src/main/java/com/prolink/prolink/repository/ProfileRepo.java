package com.prolink.prolink.repository;

import com.prolink.prolink.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long userId);
}