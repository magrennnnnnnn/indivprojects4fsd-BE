package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findByIdMessage(Long idMessage);

    List<Message> findByConversationId(Long conversationId);

    List<Message> findUnreadMessagesForProfile(Long receiverProfileId);

    Message save(Message message);
}
