package com.prolink.prolink;

import com.prolink.prolink.domain.Post;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.UpdatePostRequest;
import com.prolink.prolink.repository.PostRepository;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.service.FileStorageService;
import com.prolink.prolink.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    private MultipartFile image;

    @InjectMocks
    private PostService postService;

    @Test
    void createPost_ShouldCreatePost_WhenProfileExists() {
        Long userId = 1L;
        Long profileId = 10L;

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post savedPost = new Post(
                1L,
                "My first post",
                "This is my post text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                profileId,
                "John Doe",
                "Eindhoven",
                "/uploads/posts/image.png"
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(fileStorageService.savePostImage(image)).thenReturn("/uploads/posts/image.png");
        when(postRepository.save(any(Post.class))).thenReturn(savedPost);

        Post result = postService.createPost(
                userId,
                "My first post",
                " This is my post text ",
                image
        );

        assertEquals("My first post", result.getPostTitle());
        assertEquals("This is my post text", result.getPostText());
        assertEquals(profileId, result.getIdProfile());
        assertEquals("/uploads/posts/image.png", result.getImageUrl());

        verify(profileRepository).findByUserId(userId);
        verify(fileStorageService).savePostImage(image);
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void createPost_ShouldUseEmptyText_WhenPostTextIsNull() {
        Long userId = 1L;
        Long profileId = 10L;

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post savedPost = new Post(
                1L,
                "Image post",
                "",
                LocalDateTime.now(),
                LocalDateTime.now(),
                profileId,
                "John Doe",
                "Eindhoven",
                "/uploads/posts/image.png"
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(fileStorageService.savePostImage(image)).thenReturn("/uploads/posts/image.png");
        when(postRepository.save(any(Post.class))).thenReturn(savedPost);

        Post result = postService.createPost(
                userId,
                "Image post",
                null,
                image
        );

        assertEquals("", result.getPostText());
        assertEquals("Image post", result.getPostTitle());

        verify(profileRepository).findByUserId(userId);
        verify(fileStorageService).savePostImage(image);
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void createPost_ShouldThrowException_WhenProfileDoesNotExist() {
        Long userId = 1L;

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> postService.createPost(userId, "Title", "Text", image)
        );

        verify(profileRepository).findByUserId(userId);
        verify(fileStorageService, never()).savePostImage(any());
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void getAllPosts_ShouldReturnAllPosts() {
        Post post1 = new Post(
                1L,
                "Post 1",
                "Text 1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10L,
                "John",
                "Eindhoven",
                null
        );

        Post post2 = new Post(
                2L,
                "Post 2",
                "Text 2",
                LocalDateTime.now(),
                LocalDateTime.now(),
                20L,
                "Maria",
                "Amsterdam",
                null
        );

        when(postRepository.findAll()).thenReturn(List.of(post1, post2));

        List<Post> result = postService.getAllPosts();

        assertEquals(2, result.size());
        assertEquals("Post 1", result.get(0).getPostTitle());
        assertEquals("Post 2", result.get(1).getPostTitle());

        verify(postRepository).findAll();
    }

    @Test
    void getPostById_ShouldReturnPost_WhenPostExists() {
        Long postId = 1L;

        Post post = new Post(
                postId,
                "Existing post",
                "Existing text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10L,
                "John",
                "Eindhoven",
                null
        );

        when(postRepository.findByIdPost(postId)).thenReturn(Optional.of(post));

        Post result = postService.getPostById(postId);

        assertEquals(postId, result.getIdPost());
        assertEquals("Existing post", result.getPostTitle());

        verify(postRepository).findByIdPost(postId);
    }

    @Test
    void getPostById_ShouldThrowException_WhenPostDoesNotExist() {
        Long postId = 1L;

        when(postRepository.findByIdPost(postId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> postService.getPostById(postId)
        );

        verify(postRepository).findByIdPost(postId);
    }

    @Test
    void getPostsByProfileId_ShouldReturnPostsForProfile() {
        Long profileId = 10L;

        Post post = new Post(
                1L,
                "Profile post",
                "Text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                profileId,
                "John",
                "Eindhoven",
                null
        );

        when(postRepository.findByProfileId(profileId)).thenReturn(List.of(post));

        List<Post> result = postService.getPostsByProfileId(profileId);

        assertEquals(1, result.size());
        assertEquals(profileId, result.get(0).getIdProfile());

        verify(postRepository).findByProfileId(profileId);
    }

    @Test
    void getPostsByUserId_ShouldReturnPosts_WhenProfileExists() {
        Long userId = 1L;
        Long profileId = 10L;

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post post = new Post(
                1L,
                "User post",
                "Text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                profileId,
                "John Doe",
                "Eindhoven",
                null
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(postRepository.findByProfileId(profileId)).thenReturn(List.of(post));

        List<Post> result = postService.getPostsByUserId(userId);

        assertEquals(1, result.size());
        assertEquals("User post", result.get(0).getPostTitle());

        verify(profileRepository).findByUserId(userId);
        verify(postRepository).findByProfileId(profileId);
    }

    @Test
    void getPostsByUserId_ShouldThrowException_WhenProfileDoesNotExist() {
        Long userId = 1L;

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> postService.getPostsByUserId(userId)
        );

        verify(profileRepository).findByUserId(userId);
        verify(postRepository, never()).findByProfileId(anyLong());
    }

    @Test
    void updatePost_ShouldUpdatePost_WhenPostBelongsToUser() {
        Long userId = 1L;
        Long profileId = 10L;
        Long postId = 100L;

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post existingPost = new Post(
                postId,
                "Old title",
                "Old text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                profileId,
                "John Doe",
                "Eindhoven",
                null
        );

        UpdatePostRequest request = new UpdatePostRequest();
        request.setPostTitle("Updated title");
        request.setPostText("Updated text");

        Post updatedPost = new Post(
                postId,
                "Updated title",
                "Updated text",
                existingPost.getCreatedAt(),
                LocalDateTime.now(),
                profileId,
                "John Doe",
                "Eindhoven",
                null
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(postRepository.findByIdPost(postId)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(any(Post.class))).thenReturn(updatedPost);

        Post result = postService.updatePost(userId, postId, request);

        assertEquals("Updated title", result.getPostTitle());
        assertEquals("Updated text", result.getPostText());

        verify(profileRepository).findByUserId(userId);
        verify(postRepository).findByIdPost(postId);
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void updatePost_ShouldThrowException_WhenPostDoesNotBelongToUser() {
        Long userId = 1L;
        Long myProfileId = 10L;
        Long otherProfileId = 20L;
        Long postId = 100L;

        Profile profile = new Profile(
                myProfileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post existingPost = new Post(
                postId,
                "Other post",
                "Other text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                otherProfileId,
                "Maria",
                "Amsterdam",
                null
        );

        UpdatePostRequest request = new UpdatePostRequest();
        request.setPostTitle("Trying to update");
        request.setPostText("Trying to update text");

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(postRepository.findByIdPost(postId)).thenReturn(Optional.of(existingPost));

        assertThrows(
                ResponseStatusException.class,
                () -> postService.updatePost(userId, postId, request)
        );

        verify(profileRepository).findByUserId(userId);
        verify(postRepository).findByIdPost(postId);
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void deletePost_ShouldDeletePost_WhenPostBelongsToUser() {
        Long userId = 1L;
        Long profileId = 10L;
        Long postId = 100L;

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post existingPost = new Post(
                postId,
                "My post",
                "Text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                profileId,
                "John Doe",
                "Eindhoven",
                null
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(postRepository.findByIdPost(postId)).thenReturn(Optional.of(existingPost));

        postService.deletePost(userId, postId);

        verify(profileRepository).findByUserId(userId);
        verify(postRepository).findByIdPost(postId);
        verify(postRepository).deleteById(postId);
    }

    @Test
    void deletePost_ShouldThrowException_WhenPostDoesNotBelongToUser() {
        Long userId = 1L;
        Long myProfileId = 10L;
        Long otherProfileId = 20L;
        Long postId = 100L;

        Profile profile = new Profile(
                myProfileId,
                "John Doe",
                "Eindhoven",
                "Software student",
                userId
        );

        Post existingPost = new Post(
                postId,
                "Other post",
                "Text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                otherProfileId,
                "Maria",
                "Amsterdam",
                null
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));
        when(postRepository.findByIdPost(postId)).thenReturn(Optional.of(existingPost));

        assertThrows(
                ResponseStatusException.class,
                () -> postService.deletePost(userId, postId)
        );

        verify(profileRepository).findByUserId(userId);
        verify(postRepository).findByIdPost(postId);
        verify(postRepository, never()).deleteById(postId);
    }
}
