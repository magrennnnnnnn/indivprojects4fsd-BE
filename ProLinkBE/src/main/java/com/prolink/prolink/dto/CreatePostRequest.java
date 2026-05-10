package com.prolink.prolink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CreatePostRequest {
    @NotBlank(message = "Post title is required")
    @Size(min = 2, max = 200, message = "Post title must be between 2 and 200 characters")
    private String postTitle;

    @NotBlank(message = "Post text is required")
    @Size(min = 5, max = 10000, message = "Post text must be between 5 and 10000 characters")
    private String postText;

    public CreatePostRequest(){}

    public String getPostTitle(){return postTitle;}
    public String getPostText(){return postText;}


    public void setPostTitle(String postTitle){this.postTitle=postTitle;}
    public void setPostText(String postText){this.postText=postText;}


}
