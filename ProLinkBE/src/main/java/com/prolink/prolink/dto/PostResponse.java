package com.prolink.prolink.dto;

import com.prolink.prolink.domain.Post;

import java.time.LocalDateTime;

public class PostResponse {
    private Long idPost;
    private String postTitle;
    private String postText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long idProfile;
    private String authorName;
    private String authorLocation;

    public PostResponse() {/**/}

    public PostResponse(Long idPost,String postTitle, String postText, LocalDateTime createdAt, LocalDateTime updatedAt, Long idProfile, String authorName, String authorLocation) {
        this.idPost = idPost;
        this.postTitle = postTitle;
        this.postText = postText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.idProfile = idProfile;
        this.authorName = authorName;
        this.authorLocation = authorLocation;
    }

    public static PostResponse fromDomain(Post post) {
        return new PostResponse(
                post.getIdPost(),
                post.getPostTitle(),
                post.getPostText(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getIdProfile(),
                post.getAuthorName(),
                post.getAuthorLocation()
        );
    }

    public Long getIdPost() {return idPost;}

    public String getPostTitle() {return postTitle;}

    public String getPostText() {return postText;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public Long getIdProfile() {return idProfile;}

    public String getAuthorName() {return authorName;}

    public String getAuthorLocation() {return authorLocation;}
}
