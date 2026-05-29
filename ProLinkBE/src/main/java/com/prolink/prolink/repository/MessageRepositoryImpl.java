package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Message;
import com.prolink.prolink.entity.ConversationEntity;
import com.prolink.prolink.entity.MessageEntity;
import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final MessageJpaRepo messageJpaRepo;

    public MessageRepositoryImpl(MessageJpaRepo messageJpaRepo) {
        this.messageJpaRepo = messageJpaRepo;
    }

    @Override
    public Optional<Message> findByIdMessage(Long idMessage) {
        return messageJpaRepo.findByIdMessage(idMessage)
                .map(this::toDomain);
    }

    @Override
    public List<Message> findByConversationId(Long conversationId) {
        return messageJpaRepo
                .findByConversation_IdConversationOrderByCreatedAtAsc(conversationId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Message> findUnreadMessagesForProfile(Long receiverProfileId) {
        return messageJpaRepo
                .findByReceiverProfile_IdProfileAndReadFalseOrderByCreatedAtDesc(receiverProfileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Message save(Message message) {
        MessageEntity entity = toEntity(message);
        MessageEntity saved = messageJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Message toDomain(MessageEntity entity) {
        return new Message(
                entity.getIdMessage(),
                entity.getConversation().getIdConversation(),

                entity.getSenderProfile().getId(),
                entity.getSenderProfile().getName(),

                entity.getReceiverProfile().getId(),
                entity.getReceiverProfile().getName(),

                entity.getMessageText(),
                entity.getCreatedAt(),
                entity.isRead()
        );
    }

    private MessageEntity toEntity(Message message) {
        MessageEntity entity = new MessageEntity();

        entity.setIdMessage(message.getIdMessage());
        entity.setMessageText(message.getMessageText());
        entity.setCreatedAt(message.getCreatedAt());
        entity.setRead(message.isRead());

        ConversationEntity conversation = new ConversationEntity();
        conversation.setIdConversation(message.getConversationId());

        ProfileEntity senderProfile = new ProfileEntity();
        senderProfile.setId(message.getSenderProfileId());

        ProfileEntity receiverProfile = new ProfileEntity();
        receiverProfile.setId(message.getReceiverProfileId());

        entity.setConversation(conversation);
        entity.setSenderProfile(senderProfile);
        entity.setReceiverProfile(receiverProfile);

        return entity;
    }
}
