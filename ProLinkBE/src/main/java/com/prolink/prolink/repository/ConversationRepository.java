package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Conversation;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository {
    Optional<Conversation> findByIdConversation(Long idConversation);

    Optional<Conversation> findBetweenProfiles(Long firstProfileId, Long secondProfileId);

    List<Conversation> findAllForProfile(Long profileId);

    Conversation save(Conversation conversation);
}
