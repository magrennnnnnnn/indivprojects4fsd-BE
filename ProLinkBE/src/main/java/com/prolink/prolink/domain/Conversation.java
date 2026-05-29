package com.prolink.prolink.domain;

import java.time.LocalDateTime;

public class Conversation {
    private Long idConversation;

    private Long firstProfileId;
    private String firstProfileName;

    private Long secondProfileId;
    private String secondProfileName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Conversation(){}

    public Conversation(Long idConversation,Long firstProfileId,String firstProfileName,Long secondProfileId,String secondProfileName,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConversation=idConversation;
        this.firstProfileId=firstProfileId;
        this.firstProfileName=firstProfileName;
        this.secondProfileId=secondProfileId;
        this.secondProfileName=secondProfileName;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public Long getIdConversation() {return idConversation;}

    public Long getFirstProfileId() {return firstProfileId;}

    public String getFirstProfileName() {return firstProfileName;}

    public Long getSecondProfileId() {return secondProfileId;}

    public String getSecondProfileName() {return secondProfileName;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public void setIdConversation(Long idConversation) {this.idConversation = idConversation;}

    public void setFirstProfileId(Long firstProfileId) {this.firstProfileId = firstProfileId;}

    public void setFirstProfileName(String firstProfileName) {this.firstProfileName = firstProfileName;}

    public void setSecondProfileId(Long secondProfileId) {this.secondProfileId = secondProfileId;}

    public void setSecondProfileName(String secondProfileName) {this.secondProfileName = secondProfileName;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}
}
