package com.prolink.prolink;

import com.prolink.prolink.domain.Connection;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.SendConnectionRequest;
import com.prolink.prolink.enums.ConnectionStatusType;
import com.prolink.prolink.enums.Roles;
import com.prolink.prolink.repository.ConnectionRepository;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.service.ConnectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import com.prolink.prolink.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConnectionServiceTest {
    @Mock
    private ConnectionRepository connectionRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ConnectionService connectionService;

    @Test
    void sendConnectionRequest_ShouldCreatePendingRequest_WhenValid() {
        Long userId = 1L;
        Long requesterProfileId = 10L;
        Long receiverProfileId = 20L;

        Profile requester = new Profile(
                requesterProfileId,
                "User A",
                "Eindhoven",
                "Details A",
                userId
        );

        Profile receiver = new Profile(
                receiverProfileId,
                "User B",
                "Amsterdam",
                "Details B",
                2L
        );

        SendConnectionRequest request = new SendConnectionRequest();
        request.setReceiverProfileId(receiverProfileId);

        Connection savedConnection = new Connection();
        savedConnection.setIdConnection(1L);
        savedConnection.setRequesterProfileId(requesterProfileId);
        savedConnection.setReceiverProfileId(receiverProfileId);
        savedConnection.setStatus(ConnectionStatusType.PENDING);
        savedConnection.setCreatedAt(LocalDateTime.now());
        savedConnection.setUpdatedAt(LocalDateTime.now());

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(requester));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.of(receiver));

        User requesterUser = new User(
                userId,
                "requester@test.com",
                "password123",
                Roles.STANDARD_USER
        );

        User receiverUser = new User(
                2L,
                "receiver@test.com",
                "password123",
                Roles.STANDARD_USER
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(requesterUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiverUser));
        when(connectionRepository.findBetweenProfiles(requesterProfileId, receiverProfileId))
                .thenReturn(Optional.empty());
        when(connectionRepository.findBetweenProfiles(receiverProfileId, requesterProfileId))
                .thenReturn(Optional.empty());
        when(connectionRepository.save(any(Connection.class))).thenReturn(savedConnection);

        var result = connectionService.sendConnectionRequest(userId, request);

        assertEquals(requesterProfileId, result.getRequesterProfileId());
        assertEquals(receiverProfileId, result.getReceiverProfileId());
        assertEquals(ConnectionStatusType.PENDING, result.getStatus());

        verify(profileRepository).findByUserId(userId);
        verify(profileRepository).findByIdProfile(receiverProfileId);
        verify(connectionRepository).save(any(Connection.class));
    }

    @Test
    void sendConnectionRequest_ShouldThrowException_WhenUserSendsRequestToThemself() {
        Long userId = 1L;
        Long profileId = 10L;

        Profile profile = new Profile(
                profileId,
                "User A",
                "Eindhoven",
                "Details",
                userId
        );

        SendConnectionRequest request = new SendConnectionRequest();
        request.setReceiverProfileId(profileId);

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.of(profile));

        assertThrows(
                ResponseStatusException.class,
                () -> connectionService.sendConnectionRequest(userId, request)
        );

        verify(connectionRepository, never()).save(any(Connection.class));
    }

    @Test
    void sendConnectionRequest_ShouldThrowException_WhenConnectionAlreadyExists() {
        Long userId = 1L;
        Long requesterProfileId = 10L;
        Long receiverProfileId = 20L;

        Profile requester = new Profile(
                requesterProfileId,
                "User A",
                "Eindhoven",
                "Details A",
                userId
        );

        Profile receiver = new Profile(
                receiverProfileId,
                "User B",
                "Amsterdam",
                "Details B",
                2L
        );

        SendConnectionRequest request = new SendConnectionRequest();
        request.setReceiverProfileId(receiverProfileId);

        Connection existingConnection = new Connection();
        existingConnection.setIdConnection(1L);
        existingConnection.setRequesterProfileId(requesterProfileId);
        existingConnection.setReceiverProfileId(receiverProfileId);
        existingConnection.setStatus(ConnectionStatusType.PENDING);

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(requester));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.of(receiver));
        when(connectionRepository.findBetweenProfiles(requesterProfileId, receiverProfileId))
                .thenReturn(Optional.of(existingConnection));

        assertThrows(
                ResponseStatusException.class,
                () -> connectionService.sendConnectionRequest(userId, request)
        );

        verify(connectionRepository, never()).save(any(Connection.class));
    }

    @Test
    void acceptConnectionRequest_ShouldAcceptRequest_WhenReceiverIsLoggedInUser() {
        Long userId = 2L;
        Long requesterProfileId = 10L;
        Long receiverProfileId = 20L;
        Long connectionId = 1L;

        Profile receiverProfile = new Profile(
                receiverProfileId,
                "Receiver",
                "Amsterdam",
                "Receiver details",
                userId
        );

        Connection connection = new Connection();
        connection.setIdConnection(connectionId);
        connection.setRequesterProfileId(requesterProfileId);
        connection.setReceiverProfileId(receiverProfileId);
        connection.setStatus(ConnectionStatusType.PENDING);

        Connection savedConnection = new Connection();
        savedConnection.setIdConnection(connectionId);
        savedConnection.setRequesterProfileId(requesterProfileId);
        savedConnection.setReceiverProfileId(receiverProfileId);
        savedConnection.setStatus(ConnectionStatusType.ACCEPTED);

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(receiverProfile));
        when(connectionRepository.findByIdConnection(connectionId)).thenReturn(Optional.of(connection));
        when(connectionRepository.save(any(Connection.class))).thenReturn(savedConnection);

        var result = connectionService.acceptConnectionRequest(userId, connectionId);

        assertEquals(ConnectionStatusType.ACCEPTED, result.getStatus());

        verify(profileRepository).findByUserId(userId);
        verify(connectionRepository).findByIdConnection(connectionId);
        verify(connectionRepository).save(any(Connection.class));
    }

    @Test
    void acceptConnectionRequest_ShouldThrowException_WhenLoggedInUserIsNotReceiver() {
        Long userId = 3L;
        Long loggedInProfileId = 30L;
        Long requesterProfileId = 10L;
        Long receiverProfileId = 20L;
        Long connectionId = 1L;

        Profile wrongUserProfile = new Profile(
                loggedInProfileId,
                "Wrong User",
                "Tilburg",
                "Details",
                userId
        );

        Connection connection = new Connection();
        connection.setIdConnection(connectionId);
        connection.setRequesterProfileId(requesterProfileId);
        connection.setReceiverProfileId(receiverProfileId);
        connection.setStatus(ConnectionStatusType.PENDING);

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(wrongUserProfile));
        when(connectionRepository.findByIdConnection(connectionId)).thenReturn(Optional.of(connection));

        assertThrows(
                ResponseStatusException.class,
                () -> connectionService.acceptConnectionRequest(userId, connectionId)
        );

        verify(connectionRepository, never()).save(any(Connection.class));
    }

    @Test
    void areProfilesConnected_ShouldReturnTrue_WhenAcceptedConnectionExistsOneWay() {
        Long profileA = 10L;
        Long profileB = 20L;

        Connection connection = new Connection();
        connection.setRequesterProfileId(profileA);
        connection.setReceiverProfileId(profileB);
        connection.setStatus(ConnectionStatusType.ACCEPTED);

        when(connectionRepository.findBetweenProfiles(profileA, profileB))
                .thenReturn(Optional.of(connection));

        when(connectionRepository.findBetweenProfiles(profileB, profileA))
                .thenReturn(Optional.empty());

        boolean result = connectionService.areProfilesConnected(profileA, profileB);

        assertTrue(result);

        verify(connectionRepository).findBetweenProfiles(profileA, profileB);
        verify(connectionRepository).findBetweenProfiles(profileB, profileA);
    }

    @Test
    void areProfilesConnected_ShouldReturnTrue_WhenAcceptedConnectionExistsReverse() {
        Long profileA = 10L;
        Long profileB = 20L;

        Connection reverseConnection = new Connection();
        reverseConnection.setRequesterProfileId(profileB);
        reverseConnection.setReceiverProfileId(profileA);
        reverseConnection.setStatus(ConnectionStatusType.ACCEPTED);

        when(connectionRepository.findBetweenProfiles(profileA, profileB))
                .thenReturn(Optional.empty());

        when(connectionRepository.findBetweenProfiles(profileB, profileA))
                .thenReturn(Optional.of(reverseConnection));

        boolean result = connectionService.areProfilesConnected(profileA, profileB);

        assertTrue(result);

        verify(connectionRepository).findBetweenProfiles(profileA, profileB);
        verify(connectionRepository).findBetweenProfiles(profileB, profileA);
    }

    @Test
    void areProfilesConnected_ShouldReturnFalse_WhenNoAcceptedConnectionExists() {
        Long profileA = 10L;
        Long profileB = 20L;

        when(connectionRepository.findBetweenProfiles(profileA, profileB))
                .thenReturn(Optional.empty());

        when(connectionRepository.findBetweenProfiles(profileB, profileA))
                .thenReturn(Optional.empty());

        boolean result = connectionService.areProfilesConnected(profileA, profileB);

        assertFalse(result);

        verify(connectionRepository).findBetweenProfiles(profileA, profileB);
        verify(connectionRepository).findBetweenProfiles(profileB, profileA);
    }
}
