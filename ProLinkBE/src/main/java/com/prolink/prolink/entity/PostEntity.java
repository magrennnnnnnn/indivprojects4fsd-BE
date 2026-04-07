package com.prolink.prolink.entity;

import jakarta.persistence.Column;

public class PostEntity {
    private String postTitle;

    @Column(length = 200000000)
    private String postText;

    public PostEntity(){}

    public PostEntity(String postTitle,String postText){
        this.postTitle=postTitle;
        this.postText=postText;
    }

    public String getPostTitle(){return postTitle;}
    public String getPostText(){return postText;}

    public void setPostTitle(String postTitle){this.postTitle=postTitle;}
    public void setPostText(String postText){this.postText=postText;}
}