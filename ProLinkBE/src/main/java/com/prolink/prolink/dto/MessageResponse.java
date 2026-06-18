package com.prolink.prolink.dto;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long idMessage;
    private Long conversationId;

    private Long senderProfileId;
    private String senderProfileName;

    private Long receiverProfileId;
    private String receiverProfileName;

    private String messageText;
    private LocalDateTime createdAt;
    private boolean read;

    public MessageResponse(){/**/}

    public MessageResponse(Long idMessage,Long conversationId,Long senderProfileId,String senderProfileName,Long receiverProfileId,String receiverProfileName,String messageText,LocalDateTime createdAt,boolean read){
        this.idMessage=idMessage;
        this.conversationId=conversationId;
        this.senderProfileId=senderProfileId;
        this.senderProfileName=senderProfileName;
        this.receiverProfileId=receiverProfileId;
        this.receiverProfileName=receiverProfileName;
        this.messageText=messageText;
        this.createdAt=createdAt;
        this.read=read;
    }

    public Long getIdMessage() {return idMessage;}

    public Long getConversationId() {return conversationId;}

    public Long getSenderProfileId() {return senderProfileId;}

    public String getSenderProfileName() {return senderProfileName;}

    public Long getReceiverProfileId() {return receiverProfileId;}

    public String getReceiverProfileName() {return receiverProfileName;}

    public String getMessageText() {return messageText;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public boolean isRead() {return read;}
}
