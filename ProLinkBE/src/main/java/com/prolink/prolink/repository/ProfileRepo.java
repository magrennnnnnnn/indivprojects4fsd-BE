package com.prolink.prolink.repository;

import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUserId(Long userId);
}