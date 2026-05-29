package com.prolink.prolink.repository;

import com.prolink.prolink.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageJpaRepo extends JpaRepository<MessageEntity, Long> {
    Optional<MessageEntity> findByIdMessage(Long idMessage);

    List<MessageEntity> findByConversation_IdConversationOrderByCreatedAtAsc(Long conversationId);

    List<MessageEntity> findByReceiverProfile_IdProfileAndReadFalseOrderByCreatedAtDesc(Long receiverProfileId);
}
