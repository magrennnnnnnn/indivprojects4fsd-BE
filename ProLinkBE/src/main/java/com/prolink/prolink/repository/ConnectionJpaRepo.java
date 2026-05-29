package com.prolink.prolink.repository;

import com.prolink.prolink.entity.ConnectionEntity;
import com.prolink.prolink.enums.ConnectionStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConnectionJpaRepo extends JpaRepository<ConnectionEntity, Long> {
    Optional<ConnectionEntity> findByIdConnection(Long idConnection);

    Optional<ConnectionEntity> findByRequesterProfile_IdProfileAndReceiverProfile_IdProfile(Long requesterProfileId, Long receiverProfileId);

    List<ConnectionEntity> findByReceiverProfile_IdProfileAndStatusOrderByCreatedAtDesc(Long receiverProfileId, ConnectionStatusType status);

    List<ConnectionEntity> findByRequesterProfile_IdProfileAndStatusOrderByCreatedAtDesc(Long requesterProfileId, ConnectionStatusType status);

    List<ConnectionEntity> findByRequesterProfile_IdProfileOrReceiverProfile_IdProfile(Long requesterProfileId, Long receiverProfileId);
}
