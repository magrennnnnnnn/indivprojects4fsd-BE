package com.prolink.prolink.dto;

import java.time.LocalDateTime;

public class CreatePostRequest {
    private String postTitle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String postText;

    public CreatePostRequest(){}

    public String getPostTitle(){return postTitle;}
    public String getPostText(){return postText;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}

    public void setPostTitle(String postTitle){this.postTitle=postTitle;}
    public void setPostText(String postText){this.postText=postText;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public void setUpdatedAt(LocalDateTime updatedAt){this.updatedAt=updatedAt;}

}
