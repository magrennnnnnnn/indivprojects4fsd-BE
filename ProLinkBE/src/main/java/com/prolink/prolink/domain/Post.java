package com.prolink.prolink.domain;

import java.time.LocalDateTime;
import com.prolink.prolink.exceptionhandler.InvalidPostTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Post {
    private Long idPost;
    private String postTitle;
    private String postText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long idProfile;
    private String authorName;
    private String authorLocation;
    private String imageUrl;

    public Post(Long idPost, String postTitle, String postText,
                LocalDateTime createdAt, LocalDateTime updatedAt, Long idProfile,String authorName,String authorLocation,String imageUrl) {
        this.idPost = idPost;
        this.postTitle = postTitle;
        this.postText = postText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.idProfile = idProfile;
        this.authorName=authorName;
        this.authorLocation=authorLocation;
        this.imageUrl=imageUrl;
    }

    public Post(String postTitle, String postText,LocalDateTime createdAt, LocalDateTime updatedAt, Long idProfile,String authorName,String authorLocation,String imageUrl) {
        this.postTitle = postTitle;
        this.postText = postText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.idProfile = idProfile;
        this.authorName=authorName;
        this.authorLocation=authorLocation;
        this.imageUrl=imageUrl;
    }

    public Long getIdPost() { return idPost; }
    public String getPostTitle() { return postTitle; }
    public String getPostText() { return postText; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Long getIdProfile() { return idProfile; }
    public String getAuthorName() {return authorName; }
    public String getAuthorLocation() {return authorLocation;}
    public String getImageUrl() {return imageUrl;}


    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }
    public void setPostText(String postText) { this.postText = postText; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setIdProfile(Long idProfile) { this.idProfile = idProfile; }
    public void setAuthorName(String authorName) {this.authorName = authorName;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
    public void setAuthorLocation(String authorLocation) {this.authorLocation = authorLocation;}


    public void validatePostForCreate() {
        validateTitle();
        boolean hasText = postText != null && !postText.isBlank();
        boolean hasImage = imageUrl != null && !imageUrl.isBlank();

        if (!hasText && !hasImage) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Post must contain text or an image"
            );
        }

        if (hasText && postText.length() > 10000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Post text cannot be longer than 10000 characters"
            );
        }

    }

    public void validatePostForUpdate() {
        validateTitle();

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


}