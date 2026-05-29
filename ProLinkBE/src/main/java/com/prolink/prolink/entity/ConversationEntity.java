package com.prolink.prolink.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversations")
public class ConversationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConversation;

    @ManyToOne
    @JoinColumn(name = "first_profile_id", nullable = false)
    private ProfileEntity firstProfile;

    @ManyToOne
    @JoinColumn(name = "second_profile_id", nullable = false)
    private ProfileEntity secondProfile;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConversationEntity() {}

    public ConversationEntity(Long idConversation,ProfileEntity firstProfile,ProfileEntity secondProfile,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConversation=idConversation;
        this.firstProfile=firstProfile;
        this.secondProfile=secondProfile;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }


    public Long getIdConversation() {return idConversation;}

    public ProfileEntity getFirstProfile() {return firstProfile;}

    public ProfileEntity getSecondProfile() {return secondProfile;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public void setIdConversation(Long idConversation) {this.idConversation = idConversation;}

    public void setFirstProfile(ProfileEntity firstProfile) {this.firstProfile = firstProfile;}

    public void setSecondProfile(ProfileEntity secondProfile) {this.secondProfile = secondProfile;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}
}
