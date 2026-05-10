package com.prolink.prolink.dto;

import java.time.LocalDateTime;

public class UpdatePostRequest {
    private String postTitle;
    private String postText;

    public UpdatePostRequest(){}

    public String getPostTitle(){return postTitle;}
    public String getPostText(){return postText;}


    public void setPostTitle(String postTitle){this.postTitle=postTitle;}
    public void setPostText(String postText){this.postText=postText;}

}
