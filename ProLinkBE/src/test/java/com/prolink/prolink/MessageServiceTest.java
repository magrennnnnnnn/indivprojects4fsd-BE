package com.prolink.prolink;

import com.prolink.prolink.domain.Conversation;
import com.prolink.prolink.domain.Message;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.MessageResponse;
import com.prolink.prolink.dto.SendMessageRequest;
import com.prolink.prolink.repository.ConversationRepository;
import com.prolink.prolink.repository.MessageRepository;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.service.ConnectionService;
import com.prolink.prolink.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ConnectionService connectionService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private MessageService messageService;

    @Test
    void sendMessage_ShouldSendMessage_WhenProfilesAreConnectedAndConversationExists() {
        Long userId = 1L;
        Long senderProfileId = 10L;
        Long receiverProfileId = 20L;
        Long conversationId = 100L;

        Profile senderProfile = new Profile(
                senderProfileId,
                "Sender",
                "Eindhoven",
                "Sender details",
                userId
        );

        Profile receiverProfile = new Profile(
                receiverProfileId,
                "Receiver",
                "Amsterdam",
                "Receiver details",
                2L
        );

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(receiverProfileId);
        request.setMessageText(" Hello ");

        Conversation conversation = new Conversation(
                conversationId,
                senderProfileId,
                "Sender",
                receiverProfileId,
                "Receiver",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Message savedMessage = new Message(
                1L,
                conversationId,
                senderProfileId,
                "Sender",
                receiverProfileId,
                "Receiver",
                "Hello",
                LocalDateTime.now(),
                false
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(senderProfile));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.of(receiverProfile));
        when(connectionService.areProfilesConnected(senderProfileId, receiverProfileId)).thenReturn(true);
        when(conversationRepository.findBetweenProfiles(senderProfileId, receiverProfileId)).thenReturn(Optional.of(conversation));
        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        MessageResponse result = messageService.sendMessage(userId, request);

        assertEquals(1L, result.getIdMessage());
        assertEquals(conversationId, result.getConversationId());
        assertEquals(senderProfileId, result.getSenderProfileId());
        assertEquals(receiverProfileId, result.getReceiverProfileId());
        assertEquals("Hello", result.getMessageText());
        assertFalse(result.isRead());

        verify(profileRepository).findByUserId(userId);
        verify(profileRepository).findByIdProfile(receiverProfileId);
        verify(connectionService).areProfilesConnected(senderProfileId, receiverProfileId);
        verify(conversationRepository).findBetweenProfiles(senderProfileId, receiverProfileId);
        verify(messageRepository).save(any(Message.class));

        verify(messagingTemplate).convertAndSend(
                eq("/topic/profile/" + receiverProfileId + "/messages"),
                any(MessageResponse.class)
        );

        verify(messagingTemplate).convertAndSend(
                eq("/topic/profile/" + senderProfileId + "/messages"),
                any(MessageResponse.class)
        );
    }

    @Test
    void sendMessage_ShouldCreateConversation_WhenConversationDoesNotExist() {
        Long userId = 1L;
        Long senderProfileId = 10L;
        Long receiverProfileId = 20L;
        Long conversationId = 100L;

        Profile senderProfile = new Profile(
                senderProfileId,
                "Sender",
                "Eindhoven",
                "Sender details",
                userId
        );

        Profile receiverProfile = new Profile(
                receiverProfileId,
                "Receiver",
                "Amsterdam",
                "Receiver details",
                2L
        );

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(receiverProfileId);
        request.setMessageText("New conversation message");

        Conversation savedConversation = new Conversation(
                conversationId,
                senderProfileId,
                "Sender",
                receiverProfileId,
                "Receiver",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Message savedMessage = new Message(
                1L,
                conversationId,
                senderProfileId,
                "Sender",
                receiverProfileId,
                "Receiver",
                "New conversation message",
                LocalDateTime.now(),
                false
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(senderProfile));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.of(receiverProfile));
        when(connectionService.areProfilesConnected(senderProfileId, receiverProfileId)).thenReturn(true);
        when(conversationRepository.findBetweenProfiles(senderProfileId, receiverProfileId)).thenReturn(Optional.empty());
        when(conversationRepository.save(any(Conversation.class))).thenReturn(savedConversation);
        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        MessageResponse result = messageService.sendMessage(userId, request);

        assertEquals(conversationId, result.getConversationId());
        assertEquals("New conversation message", result.getMessageText());

        verify(conversationRepository).save(any(Conversation.class));
        verify(messageRepository).save(any(Message.class));
    }

    @Test
    void sendMessage_ShouldThrowException_WhenSenderProfileDoesNotExist() {
        Long userId = 1L;

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(20L);
        request.setMessageText("Hello");

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> messageService.sendMessage(userId, request)
        );

        verify(profileRepository).findByUserId(userId);
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessage_ShouldThrowException_WhenReceiverProfileDoesNotExist() {
        Long userId = 1L;
        Long senderProfileId = 10L;
        Long receiverProfileId = 20L;

        Profile senderProfile = new Profile(
                senderProfileId,
                "Sender",
                "Eindhoven",
                "Sender details",
                userId
        );

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(receiverProfileId);
        request.setMessageText("Hello");

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(senderProfile));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> messageService.sendMessage(userId, request)
        );

        verify(profileRepository).findByUserId(userId);
        verify(profileRepository).findByIdProfile(receiverProfileId);
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessage_ShouldThrowException_WhenUserMessagesThemself() {
        Long userId = 1L;
        Long profileId = 10L;

        Profile profile = new Profile(
                profileId,
                "Sender",
                "Eindhoven",
                "Sender details",
                userId
        );

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(profileId);
        request.setMessageText("Hello myself");

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.of(profile));

        assertThrows(
                ResponseStatusException.class,
                () -> messageService.sendMessage(userId, request)
        );

        verify(connectionService, never()).areProfilesConnected(anyLong(), anyLong());
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessage_ShouldThrowException_WhenProfilesAreNotConnected() {
        Long userId = 1L;
        Long senderProfileId = 10L;
        Long receiverProfileId = 20L;

        Profile senderProfile = new Profile(
                senderProfileId,
                "Sender",
                "Eindhoven",
                "Sender details",
                userId
        );

        Profile receiverProfile = new Profile(
                receiverProfileId,
                "Receiver",
                "Amsterdam",
                "Receiver details",
                2L
        );

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(receiverProfileId);
        request.setMessageText("Hello");

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(senderProfile));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.of(receiverProfile));
        when(connectionService.areProfilesConnected(senderProfileId, receiverProfileId)).thenReturn(false);

        assertThrows(
                ResponseStatusException.class,
                () -> messageService.sendMessage(userId, request)
        );

        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void sendMessage_ShouldThrowException_WhenMessageTextIsBlank() {
        Long userId = 1L;
        Long senderProfileId = 10L;
        Long receiverProfileId = 20L;

        Profile senderProfile = new Profile(
                senderProfileId,
                "Sender",
                "Eindhoven",
                "Sender details",
                userId
        );

        Profile receiverProfile = new Profile(
                receiverProfileId,
                "Receiver",
                "Amsterdam",
                "Receiver details",
                2L
        );

        SendMessageRequest request = new SendMessageRequest();
        request.setReceiverProfileId(receiverProfileId);
        request.setMessageText("   ");

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(senderProfile));
        when(profileRepository.findByIdProfile(receiverProfileId)).thenReturn(Optional.of(receiverProfile));
        when(connectionService.areProfilesConnected(senderProfileId, receiverProfileId)).thenReturn(true);

        assertThrows(
                ResponseStatusException.class,
                () -> messageService.sendMessage(userId, request)
        );

        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void getMessagesWithProfile_ShouldReturnMessages_WhenConversationExists() {
        Long userId = 1L;
        Long myProfileId = 10L;
        Long otherProfileId = 20L;
        Long conversationId = 100L;

        Profile myProfile = new Profile(
                myProfileId,
                "Me",
                "Eindhoven",
                "My details",
                userId
        );

        Profile otherProfile = new Profile(
                otherProfileId,
                "Other",
                "Amsterdam",
                "Other details",
                2L
        );

        Conversation conversation = new Conversation(
                conversationId,
                myProfileId,
                "Me",
                otherProfileId,
                "Other",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Message message = new Message(
                1L,
                conversationId,
                myProfileId,
                "Me",
                otherProfileId,
                "Other",
                "Hello",
                LocalDateTime.now(),
                false
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(myProfile));
        when(profileRepository.findByIdProfile(otherProfileId)).thenReturn(Optional.of(otherProfile));
        when(connectionService.areProfilesConnected(myProfileId, otherProfileId)).thenReturn(true);
        when(conversationRepository.findBetweenProfiles(myProfileId, otherProfileId)).thenReturn(Optional.of(conversation));
        when(messageRepository.findByConversationId(conversationId)).thenReturn(List.of(message));

        List<MessageResponse> result = messageService.getMessagesWithProfile(userId, otherProfileId);

        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getMessageText());
        assertEquals(myProfileId, result.get(0).getSenderProfileId());
        assertEquals(otherProfileId, result.get(0).getReceiverProfileId());

        verify(messageRepository).findByConversationId(conversationId);
    }

    @Test
    void getMessagesWithProfile_ShouldReturnEmptyList_WhenConversationDoesNotExist() {
        Long userId = 1L;
        Long myProfileId = 10L;
        Long otherProfileId = 20L;

        Profile myProfile = new Profile(
                myProfileId,
                "Me",
                "Eindhoven",
                "My details",
                userId
        );

        Profile otherProfile = new Profile(
                otherProfileId,
                "Other",
                "Amsterdam",
                "Other details",
                2L
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(myProfile));
        when(profileRepository.findByIdProfile(otherProfileId)).thenReturn(Optional.of(otherProfile));
        when(connectionService.areProfilesConnected(myProfileId, otherProfileId)).thenReturn(true);
        when(conversationRepository.findBetweenProfiles(myProfileId, otherProfileId)).thenReturn(Optional.empty());

        List<MessageResponse> result = messageService.getMessagesWithProfile(userId, otherProfileId);

        assertTrue(result.isEmpty());

        verify(messageRepository, never()).findByConversationId(anyLong());
    }

    @Test
    void getMessagesWithProfile_ShouldThrowException_WhenProfilesAreNotConnected() {
        Long userId = 1L;
        Long myProfileId = 10L;
        Long otherProfileId = 20L;

        Profile myProfile = new Profile(
                myProfileId,
                "Me",
                "Eindhoven",
                "My details",
                userId
        );

        Profile otherProfile = new Profile(
                otherProfileId,
                "Other",
                "Amsterdam",
                "Other details",
                2L
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(myProfile));
        when(profileRepository.findByIdProfile(otherProfileId)).thenReturn(Optional.of(otherProfile));
        when(connectionService.areProfilesConnected(myProfileId, otherProfileId)).thenReturn(false);

        assertThrows(
                ResponseStatusException.class,
                () -> messageService.getMessagesWithProfile(userId, otherProfileId)
        );

        verify(conversationRepository, never()).findBetweenProfiles(anyLong(), anyLong());
    }

    @Test
    void sendLiveMessage_ShouldSendToReceiverAndSenderTopics() {
        MessageResponse response = new MessageResponse(
                1L,
                100L,
                10L,
                "Sender",
                20L,
                "Receiver",
                "Hello",
                LocalDateTime.now(),
                false
        );

        messageService.sendLiveMessage(response);

        verify(messagingTemplate).convertAndSend(
                "/topic/profile/20/messages",
                response
        );

        verify(messagingTemplate).convertAndSend(
                "/topic/profile/10/messages",
                response
        );
    }
}
