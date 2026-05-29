package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Conversation;
import com.prolink.prolink.entity.ConversationEntity;
import com.prolink.prolink.entity.ProfileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConversationRepositoryImpl implements ConversationRepository {
    private final ConversationJpaRepo conversationJpaRepo;

    public ConversationRepositoryImpl(ConversationJpaRepo conversationJpaRepo) {
        this.conversationJpaRepo = conversationJpaRepo;
    }

    @Override
    public Optional<Conversation> findByIdConversation(Long idConversation) {
        return conversationJpaRepo.findByIdConversation(idConversation)
                .map(this::toDomain);
    }

    @Override
    public Optional<Conversation> findBetweenProfiles(Long firstProfileId, Long secondProfileId) {
        Optional<ConversationEntity> oneWay = conversationJpaRepo
                .findByFirstProfile_IdProfileAndSecondProfile_IdProfile(firstProfileId, secondProfileId);

        if (oneWay.isPresent()) {
            return oneWay.map(this::toDomain);
        }

        return conversationJpaRepo
                .findByFirstProfile_IdProfileAndSecondProfile_IdProfile(secondProfileId, firstProfileId)
                .map(this::toDomain);
    }

    @Override
    public List<Conversation> findAllForProfile(Long profileId) {
        return conversationJpaRepo
                .findByFirstProfile_IdProfileOrSecondProfile_IdProfile(profileId, profileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Conversation save(Conversation conversation) {
        ConversationEntity entity = toEntity(conversation);
        ConversationEntity saved = conversationJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Conversation toDomain(ConversationEntity entity) {
        return new Conversation(
                entity.getIdConversation(),
                entity.getFirstProfile().getId(),
                entity.getFirstProfile().getName(),
                entity.getSecondProfile().getId(),
                entity.getSecondProfile().getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private ConversationEntity toEntity(Conversation conversation) {
        ConversationEntity entity = new ConversationEntity();

        entity.setIdConversation(conversation.getIdConversation());
        entity.setCreatedAt(conversation.getCreatedAt());
        entity.setUpdatedAt(conversation.getUpdatedAt());

        ProfileEntity firstProfile = new ProfileEntity();
        firstProfile.setId(conversation.getFirstProfileId());

        ProfileEntity secondProfile = new ProfileEntity();
        secondProfile.setId(conversation.getSecondProfileId());

        entity.setFirstProfile(firstProfile);
        entity.setSecondProfile(secondProfile);

        return entity;
    }


}
