package com.prolink.prolink.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMessage;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private ConversationEntity conversation;

    @ManyToOne
    @JoinColumn(name = "sender_profile_id", nullable = false)
    private ProfileEntity senderProfile;

    @ManyToOne
    @JoinColumn(name = "receiver_profile_id", nullable = false)
    private ProfileEntity receiverProfile;

    @Column( nullable = false)
    private String messageText;

    private LocalDateTime createdAt;

    @Column(name = "is_read")
    private boolean read;

    public MessageEntity() {}

    public MessageEntity(Long idMessage,ConversationEntity conversation,ProfileEntity senderProfile,ProfileEntity receiverProfile,String messageText,LocalDateTime createdAt,boolean read){
        this.idMessage=idMessage;
        this.conversation=conversation;
        this.senderProfile=senderProfile;
        this.receiverProfile=receiverProfile;
        this.messageText=messageText;
        this.createdAt=createdAt;
        this.read=read;
    }

    public Long getIdMessage() {
        return idMessage;
    }

    public ConversationEntity getConversation() {
        return conversation;
    }

    public ProfileEntity getSenderProfile() {
        return senderProfile;
    }

    public ProfileEntity getReceiverProfile() {return receiverProfile;}

    public String getMessageText() {return messageText;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public boolean isRead() {return read;}

    public void setIdMessage(Long idMessage) {this.idMessage = idMessage;}

    public void setConversation(ConversationEntity conversation) {this.conversation = conversation;}

    public void setSenderProfile(ProfileEntity senderProfile) {this.senderProfile = senderProfile;}

    public void setReceiverProfile(ProfileEntity receiverProfile) {this.receiverProfile = receiverProfile;}

    public void setMessageText(String messageText) {this.messageText = messageText;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public void setRead(boolean read) {this.read = read;}
}
