package com.prolink.prolink.domain;

import java.time.LocalDateTime;
import com.prolink.prolink.exceptionhandler.InvalidPostTitleException;
import com.prolink.prolink.exceptionhandler.InvalidPostTextException;

public class Post {
    private Long idPost;
    private String postTitle;
    private String postText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long idProfile;

    public Post(Long idPost, String postTitle, String postText,
                LocalDateTime createdAt, LocalDateTime updatedAt, Long idProfile) {
        this.idPost = idPost;
        this.postTitle = postTitle;
        this.postText = postText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.idProfile = idProfile;
    }

    public Post(String postTitle, String postText,
                LocalDateTime createdAt, LocalDateTime updatedAt, Long idProfile) {
        this.postTitle = postTitle;
        this.postText = postText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.idProfile = idProfile;
    }

    public Long getIdPost() { return idPost; }
    public String getPostTitle() { return postTitle; }
    public String getPostText() { return postText; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Long getIdProfile() { return idProfile; }

    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }
    public void setPostText(String postText) { this.postText = postText; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setIdProfile(Long idProfile) { this.idProfile = idProfile; }


    public void validatePostForCreate() {
        validateTitle();
        validateText();
    }

    public void validatePostForUpdate() {
        validateTitle();
        validateText();
    }

    private void validateTitle() {
        if (postTitle == null || postTitle.isBlank()) {
            throw new InvalidPostTitleException("Post title can not be empty");
        }

        if (postTitle.length() < 2) {
            throw new InvalidPostTitleException("Post title must be at least 2 characters long");
        }

        if (postTitle.length() > 200) {
            throw new InvalidPostTitleException("Post title can not be longer than 200 characters");
        }
    }

    private void validateText() {
        if (postText == null || postText.isBlank()) {
            throw new InvalidPostTextException("Post text can not be empty");
        }

        if (postText.length() < 5) {
            throw new InvalidPostTextException("Post text must be at least 5 characters long");
        }

        if (postText.length() > 10000) {
            throw new InvalidPostTextException("Post text can not be longer than 10000 characters");
        }
    }
}