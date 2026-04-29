package com.prolink.prolink.service;
import com.prolink.prolink.domain.Post;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.CreatePostRequest;
import com.prolink.prolink.repository.PostRepository;
import com.prolink.prolink.repository.ProfileRepository;
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
                profile.getIdProfile()
        );

        return postRepository.save(post);
    }

    public List<Post> getPostsByProfileId(Long profileId) {
        return postRepository.findByProfileId(profileId);
    }
}
