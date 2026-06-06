package com.prolink.prolink.controller;

import com.prolink.prolink.config.SessionService;
import com.prolink.prolink.domain.Post;
import com.prolink.prolink.dto.CreatePostRequest;
import com.prolink.prolink.dto.UpdatePostRequest;
import com.prolink.prolink.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final SessionService sessionService;

    public PostController(PostService postService, SessionService sessionService) {
        this.postService = postService;
        this.sessionService = sessionService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestParam("postTitle") String postTitle, @RequestParam("postText") String postText, @RequestParam(value = "image", required = false) MultipartFile image, HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return postService.createPost(userId, postTitle, postText, image);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/profile/{profileId}")
    public List<Post> getPostsByProfileId(@PathVariable Long profileId) {
        return postService.getPostsByProfileId(profileId);
    }

    @GetMapping("/me")
    public List<Post> getMyPosts(HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return postService.getPostsByUserId(userId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody UpdatePostRequest request, HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return postService.updatePost(userId, postId, request);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long postId, HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        postService.deletePost(userId, postId);
    }

}