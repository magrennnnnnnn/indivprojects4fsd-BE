package com.prolink.prolink.service;

import com.prolink.prolink.domain.Connection;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.ConnectionResponse;
import com.prolink.prolink.dto.SendConnectionRequest;
import com.prolink.prolink.enums.ConnectionStatusType;
import com.prolink.prolink.repository.ConnectionRepository;
import com.prolink.prolink.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final ProfileRepository profileRepository;

    public ConnectionService(ConnectionRepository connectionRepository,
                             ProfileRepository profileRepository) {
        this.connectionRepository = connectionRepository;
        this.profileRepository = profileRepository;
    }

    public ConnectionResponse sendConnectionRequest(Long userId, SendConnectionRequest request) {
        Profile requesterProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Profile receiverProfile = profileRepository.findByIdProfile(request.getReceiverProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver profile not found"));

        if (requesterProfile.getIdProfile().equals(receiverProfile.getIdProfile())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot send a connection request to yourself");
        }

        boolean alreadyExistsOneWay = connectionRepository
                .findBetweenProfiles(
                        requesterProfile.getIdProfile(),
                        receiverProfile.getIdProfile()
                )
                .isPresent();

        boolean alreadyExistsReverse = connectionRepository
                .findBetweenProfiles(
                        receiverProfile.getIdProfile(),
                        requesterProfile.getIdProfile()
                )
                .isPresent();

        if (alreadyExistsOneWay || alreadyExistsReverse) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Connection request already exists");
        }

        Connection connection = new Connection();
        connection.setRequesterProfileId(requesterProfile.getIdProfile());
        connection.setReceiverProfileId(receiverProfile.getIdProfile());
        connection.setStatus(ConnectionStatusType.PENDING);
        connection.setCreatedAt(LocalDateTime.now());
        connection.setUpdatedAt(LocalDateTime.now());

        Connection savedConnection = connectionRepository.save(connection);

        return ConnectionResponse.fromDomain(savedConnection);
    }

    public ConnectionResponse acceptConnectionRequest(Long userId, Long connectionId) {
        Profile receiverProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Connection connection = connectionRepository.findByIdConnection(connectionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Connection request not found"));

        if (!connection.getReceiverProfileId().equals(receiverProfile.getIdProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only accept requests sent to your profile");
        }

        if (connection.getStatus() != ConnectionStatusType.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only pending requests can be accepted");
        }

        connection.setStatus(ConnectionStatusType.ACCEPTED);
        connection.setUpdatedAt(LocalDateTime.now());

        Connection updatedConnection = connectionRepository.save(connection);

        return ConnectionResponse.fromDomain(updatedConnection);
    }

    public ConnectionResponse declineConnectionRequest(Long userId, Long connectionId) {
        Profile receiverProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Connection connection = connectionRepository.findByIdConnection(connectionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Connection request not found"));

        if (!connection.getReceiverProfileId().equals(receiverProfile.getIdProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only decline requests sent to your profile");
        }

        if (connection.getStatus() != ConnectionStatusType.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only pending requests can be declined");
        }

        connection.setStatus(ConnectionStatusType.DECLINED);
        connection.setUpdatedAt(LocalDateTime.now());

        Connection updatedConnection = connectionRepository.save(connection);

        return ConnectionResponse.fromDomain(updatedConnection);
    }

    public List<ConnectionResponse> getReceivedPendingRequests(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return connectionRepository
                .findReceivedByStatus(profile.getIdProfile(), ConnectionStatusType.PENDING)
                .stream()
                .map(ConnectionResponse::fromDomain)
                .toList();
    }

    public List<ConnectionResponse> getSentPendingRequests(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return connectionRepository
                .findSentByStatus(profile.getIdProfile(), ConnectionStatusType.PENDING)
                .stream()
                .map(ConnectionResponse::fromDomain)
                .toList();
    }

    public List<ConnectionResponse> getMyConnections(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return connectionRepository
                .findAllForProfile(profile.getIdProfile())
                .stream()
                .filter(connection -> connection.getStatus() == ConnectionStatusType.ACCEPTED)
                .map(ConnectionResponse::fromDomain)
                .toList();
    }

    public boolean areProfilesConnected(Long firstProfileId, Long secondProfileId) {
        boolean connectedOneWay = connectionRepository
                .findBetweenProfiles(firstProfileId, secondProfileId)
                .filter(connection -> connection.getStatus() == ConnectionStatusType.ACCEPTED)
                .isPresent();

        boolean connectedReverse = connectionRepository
                .findBetweenProfiles(secondProfileId, firstProfileId)
                .filter(connection -> connection.getStatus() == ConnectionStatusType.ACCEPTED)
                .isPresent();

        return connectedOneWay || connectedReverse;
    }
}