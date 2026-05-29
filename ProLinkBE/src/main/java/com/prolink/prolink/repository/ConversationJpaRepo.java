package com.prolink.prolink.repository;

import com.prolink.prolink.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationJpaRepo extends JpaRepository<ConversationEntity, Long> {
    Optional<ConversationEntity> findByIdConversation(Long idConversation);

    Optional<ConversationEntity> findByFirstProfile_IdProfileAndSecondProfile_IdProfile(Long firstProfileId, Long secondProfileId);

    List<ConversationEntity> findByFirstProfile_IdProfileOrSecondProfile_IdProfile(Long firstProfileId, Long secondProfileId);
}
