package com.prolink.prolink.repository;

import com.prolink.prolink.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmail(String email);
}