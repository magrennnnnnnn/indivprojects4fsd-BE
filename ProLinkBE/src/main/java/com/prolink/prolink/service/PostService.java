package com.prolink.prolink.service;
import com.prolink.prolink.domain.Post;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.CreatePostRequest;
import com.prolink.prolink.repository.PostRepository;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.dto.UpdatePostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;

    public PostService(PostRepository postRepository, ProfileRepository profileRepository) {
        this.postRepository = postRepository;
        this.profileRepository = profileRepository;
    }

    public Post createPost(Long userId, CreatePostRequest request) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Post post = new Post(
                request.getPostTitle(),
                request.getPostText(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                profile.getIdProfile(),
                profile.getName(),
                profile.getLocation()
        );

        post.validatePostForCreate();

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findByIdPost(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    public List<Post> getPostsByProfileId(Long profileId) {
        return postRepository.findByProfileId(profileId);
    }

    public List<Post> getPostsByUserId(Long userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return postRepository.findByProfileId(profile.getIdProfile());
    }

    public Post updatePost(Long userId, Long postId, UpdatePostRequest request) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Post existingPost = postRepository.findByIdPost(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        if (!existingPost.getIdProfile().equals(profile.getIdProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own posts");
        }

        existingPost.setPostTitle(request.getPostTitle());
        existingPost.setPostText(request.getPostText());
        existingPost.setUpdatedAt(LocalDateTime.now());

        existingPost.validatePostForUpdate();

        return postRepository.save(existingPost);
    }

    public void deletePost(Long userId, Long postId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Post existingPost = postRepository.findByIdPost(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        if (!existingPost.getIdProfile().equals(profile.getIdProfile())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own posts");
        }

        postRepository.deleteById(postId);
    }
}
