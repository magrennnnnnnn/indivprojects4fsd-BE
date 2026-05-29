package com.prolink.prolink.service;

import com.prolink.prolink.domain.Conversation;
import com.prolink.prolink.domain.Message;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.MessageResponse;
import com.prolink.prolink.dto.SendMessageRequest;
import com.prolink.prolink.repository.ConversationRepository;
import com.prolink.prolink.repository.MessageRepository;
import com.prolink.prolink.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    private final ConnectionService connectionService;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(MessageRepository messageRepository,
                          ConversationRepository conversationRepository,
                          ProfileRepository profileRepository,
                          ConnectionService connectionService,
                          SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
        this.connectionService = connectionService;
        this.messagingTemplate = messagingTemplate;
    }

    public MessageResponse sendMessage(Long userId, SendMessageRequest request) {
        Profile senderProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender profile not found"));

        Profile receiverProfile = profileRepository.findByIdProfile(request.getReceiverProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver profile not found"));

        if (senderProfile.getIdProfile().equals(receiverProfile.getIdProfile())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot message yourself");
        }

        boolean connected = connectionService.areProfilesConnected(
                senderProfile.getIdProfile(),
                receiverProfile.getIdProfile()
        );

        if (!connected) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only message your connections");
        }

        String messageText = request.getMessageText();

        if (messageText == null || messageText.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message text is required");
        }

        Conversation conversation = findOrCreateConversation(senderProfile, receiverProfile);

        Message message = new Message(
                null,
                conversation.getIdConversation(),
                senderProfile.getIdProfile(),
                senderProfile.getName(),
                receiverProfile.getIdProfile(),
                receiverProfile.getName(),
                messageText.trim(),
                LocalDateTime.now(),
                false
        );

        Message savedMessage = messageRepository.save(message);

        MessageResponse response = toResponse(savedMessage);

        sendLiveMessage(response);

        return response;
    }

    public List<MessageResponse> getMessagesWithProfile(Long userId, Long otherProfileId) {
        Profile myProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Profile otherProfile = profileRepository.findByIdProfile(otherProfileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Other profile not found"));

        boolean connected = connectionService.areProfilesConnected(
                myProfile.getIdProfile(),
                otherProfile.getIdProfile()
        );

        if (!connected) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only view messages with your connections");
        }

        Conversation conversation = conversationRepository
                .findBetweenProfiles(myProfile.getIdProfile(), otherProfile.getIdProfile())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found"));

        return messageRepository.findByConversationId(conversation.getIdConversation())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Conversation findOrCreateConversation(Profile senderProfile, Profile receiverProfile) {
        return conversationRepository
                .findBetweenProfiles(senderProfile.getIdProfile(), receiverProfile.getIdProfile())
                .orElseGet(() -> createConversation(senderProfile, receiverProfile));
    }

    private Conversation createConversation(Profile senderProfile, Profile receiverProfile) {
        Conversation conversation = new Conversation();

        conversation.setFirstProfileId(senderProfile.getIdProfile());
        conversation.setFirstProfileName(senderProfile.getName());

        conversation.setSecondProfileId(receiverProfile.getIdProfile());
        conversation.setSecondProfileName(receiverProfile.getName());

        conversation.setCreatedAt(LocalDateTime.now());
        conversation.setUpdatedAt(LocalDateTime.now());

        return conversationRepository.save(conversation);
    }

    public void sendLiveMessage(MessageResponse message) {
        messagingTemplate.convertAndSend(
                "/topic/profile/" + message.getReceiverProfileId() + "/messages",
                message
        );

        messagingTemplate.convertAndSend(
                "/topic/profile/" + message.getSenderProfileId() + "/messages",
                message
        );
    }

    private MessageResponse toResponse(Message message) {
        return new MessageResponse(
                message.getIdMessage(),
                message.getConversationId(),
                message.getSenderProfileId(),
                message.getSenderProfileName(),
                message.getReceiverProfileId(),
                message.getReceiverProfileName(),
                message.getMessageText(),
                message.getCreatedAt(),
                message.isRead()
        );
    }
}