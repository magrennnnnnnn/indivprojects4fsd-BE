package com.prolink.prolink.repository;

import com.prolink.prolink.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepo extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByIdPost(Long idPost);
    List<PostEntity> findByProfileEntity_IdProfile(Long profileId);
}
