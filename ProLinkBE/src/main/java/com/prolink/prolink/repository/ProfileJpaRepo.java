package com.prolink.prolink.repository;

import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepo extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByIdProfile(Long idProfile);
    Optional<ProfileEntity> findByUserEntity_Id(Long userId);
}