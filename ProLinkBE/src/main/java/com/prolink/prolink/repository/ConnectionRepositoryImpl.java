package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Connection;
import com.prolink.prolink.entity.ConnectionEntity;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.enums.ConnectionStatusType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConnectionRepositoryImpl implements ConnectionRepository {

    private final ConnectionJpaRepo connectionJpaRepo;

    public ConnectionRepositoryImpl(ConnectionJpaRepo connectionJpaRepo) {
        this.connectionJpaRepo = connectionJpaRepo;
    }

    @Override
    public Optional<Connection> findByIdConnection(Long idConnection) {
        return connectionJpaRepo.findByIdConnection(idConnection)
                .map(this::toDomain);
    }

    @Override
    public Optional<Connection> findBetweenProfiles(Long requesterProfileId, Long receiverProfileId) {
        return connectionJpaRepo
                .findByRequesterProfile_IdProfileAndReceiverProfile_IdProfile(requesterProfileId, receiverProfileId)
                .map(this::toDomain);
    }

    @Override
    public List<Connection> findReceivedByStatus(Long receiverProfileId, ConnectionStatusType status) {
        return connectionJpaRepo
                .findByReceiverProfile_IdProfileAndStatusOrderByCreatedAtDesc(receiverProfileId, status)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Connection> findSentByStatus(Long requesterProfileId, ConnectionStatusType status) {
        return connectionJpaRepo
                .findByRequesterProfile_IdProfileAndStatusOrderByCreatedAtDesc(requesterProfileId, status)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Connection> findAllForProfile(Long profileId) {
        return connectionJpaRepo
                .findByRequesterProfile_IdProfileOrReceiverProfile_IdProfile(profileId, profileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Connection save(Connection connection) {
        ConnectionEntity entity = toEntity(connection);
        ConnectionEntity saved = connectionJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Connection toDomain(ConnectionEntity entity) {
        return new Connection(
                entity.getIdConnection(),

                entity.getRequesterProfile().getId(),
                entity.getRequesterProfile().getName(),
                entity.getRequesterProfile().getLocation(),

                entity.getReceiverProfile().getId(),
                entity.getReceiverProfile().getName(),
                entity.getReceiverProfile().getLocation(),

                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    private ConnectionEntity toEntity(Connection connection) {
        ConnectionEntity entity = new ConnectionEntity();

        entity.setIdConnection(connection.getIdConnection());
        entity.setStatus(connection.getStatus());
        entity.setCreatedAt(connection.getCreatedAt());
        entity.setUpdatedAt(connection.getUpdatedAt());

        ProfileEntity requester = new ProfileEntity();
        requester.setId(connection.getRequesterProfileId());

        ProfileEntity receiver = new ProfileEntity();
        receiver.setId(connection.getReceiverProfileId());

        entity.setRequesterProfile(requester);
        entity.setReceiverProfile(receiver);

        return entity;
    }
}