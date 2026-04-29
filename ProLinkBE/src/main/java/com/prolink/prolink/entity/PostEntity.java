package com.prolink.prolink.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    private String postTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(length = 10000)
    private String postText;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private ProfileEntity profileEntity;

    public PostEntity() {}

    public PostEntity(String postTitle, String postText, ProfileEntity profileEntity) {
        this.postTitle = postTitle;
        this.postText = postText;
        this.profileEntity = profileEntity;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getIdPost() { return idPost; }
    public String getPostTitle() { return postTitle; }
    public String getPostText() { return postText; }
    public ProfileEntity getProfileEntity() { return profileEntity; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setIdPost(Long idPost) { this.idPost = idPost; }
    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }
    public void setPostText(String postText) { this.postText = postText; }
    public void setProfileEntity(ProfileEntity profileEntity) { this.profileEntity = profileEntity; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}