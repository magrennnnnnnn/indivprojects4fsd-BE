package com.prolink.prolink.dto;


public class PostRequest {
    private String postTitle;
    private String postText;

    public String getPostTitle(){return postTitle;}
    public String getPostText(){return postText;}

    public void setPostTitle(String postTitle){this.postTitle=postTitle;}
    public void setPostText(String postText){this.postText=postText;}
}
