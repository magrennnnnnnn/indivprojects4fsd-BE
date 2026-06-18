package com.prolink.prolink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SendMessageRequest {
    @NotNull(message = "Receiver profile id is required")
    private Long receiverProfileId;

    @NotBlank(message = "Message text is required")
    @Size(min = 1, max = 2000, message = "Message must be between 1 and 2000 characters")
    private String messageText;

    public SendMessageRequest(){/**/}

    public Long getReceiverProfileId() {return receiverProfileId;}

    public String getMessageText() {return messageText;}

    public void setReceiverProfileId(Long receiverProfileId) {this.receiverProfileId = receiverProfileId;}

    public void setMessageText(String messageText) {this.messageText = messageText;}
}
