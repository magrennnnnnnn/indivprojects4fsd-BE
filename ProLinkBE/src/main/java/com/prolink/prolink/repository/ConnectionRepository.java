package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Connection;
import com.prolink.prolink.enums.ConnectionStatusType;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    Optional<Connection> findByIdConnection(Long idConnection);

    Optional<Connection> findBetweenProfiles(Long requesterProfileId, Long receiverProfileId);

    List<Connection> findReceivedByStatus(Long receiverProfileId, ConnectionStatusType status);

    List<Connection> findSentByStatus(Long requesterProfileId, ConnectionStatusType status);

    List<Connection> findAllForProfile(Long profileId);

    Connection save(Connection connection);
}
