package com.prolink.prolink.dto;

import java.time.LocalDateTime;

public class CreatePostRequest {
    private String postTitle;
    private String postText;

    public CreatePostRequest(){}

    public String getPostTitle(){return postTitle;}
    public String getPostText(){return postText;}


    public void setPostTitle(String postTitle){this.postTitle=postTitle;}
    public void setPostText(String postText){this.postText=postText;}


}
