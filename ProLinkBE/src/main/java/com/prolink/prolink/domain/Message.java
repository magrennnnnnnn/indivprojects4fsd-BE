package com.prolink.prolink.domain;

import java.time.LocalDateTime;

public class Message {
    private Long idMessage;
    private Long conversationId;

    private Long senderProfileId;
    private String senderProfileName;

    private Long receiverProfileId;
    private String receiverProfileName;

    private String messageText;
    private LocalDateTime createdAt;
    private boolean read;

    @SuppressWarnings("java:S107")
    public Message(Long idMessage,Long conversationId,Long senderProfileId,String senderProfileName,Long receiverProfileId,String receiverProfileName,String messageText,LocalDateTime createdAt,boolean read){
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

    public Long getIdMessage(){return idMessage;}
    public Long getConversationId(){return conversationId;}
    public Long getSenderProfileId(){return senderProfileId;}
    public String getSenderProfileName(){return senderProfileName;}
    public Long getReceiverProfileId(){return receiverProfileId;}
    public String getReceiverProfileName(){return receiverProfileName;}
    public String getMessageText(){return messageText;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public boolean isRead() {return read;}

    public void setIdMessage(Long idMessage){this.idMessage=idMessage;}
    public void setConversationId(Long conversationId){this.conversationId=conversationId;}
    public void setSenderProfileId(Long senderProfileId){this.senderProfileId=senderProfileId;}
    public void setSenderProfileName(String senderProfileName){this.senderProfileName=senderProfileName;}
    public void setReceiverProfileId(Long receiverProfileId){this.receiverProfileId=receiverProfileId;}
    public void setReceiverProfileName(String receiverProfileName){this.receiverProfileName=receiverProfileName;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public void setMessageText(String messageText){this.messageText=messageText;}
    public void setRead(boolean read){this.read=read;}
}
