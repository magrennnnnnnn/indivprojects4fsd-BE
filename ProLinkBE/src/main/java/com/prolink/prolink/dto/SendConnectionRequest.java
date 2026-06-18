package com.prolink.prolink.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SendConnectionRequest {
    @NotNull(message = "Receiver profile id is required")
    @Positive(message = "Receiver profile id must be positive")
    private Long receiverProfileId;

    public SendConnectionRequest() {/**/}

    public Long getReceiverProfileId() {
        return receiverProfileId;
    }

    public void setReceiverProfileId(Long receiverProfileId) {
        this.receiverProfileId = receiverProfileId;
    }
}
