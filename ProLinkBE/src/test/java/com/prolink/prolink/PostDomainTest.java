package com.prolink.prolink;

import com.prolink.prolink.domain.Post;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PostDomainTest {

    @Test
    void validatePostForCreate_ShouldPass_WhenTitleAndTextAreValid() {
        Post post = new Post(
                "Valid title",
                "Valid post text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L,
                "John",
                "Eindhoven",
                null
        );

        assertDoesNotThrow(post::validatePostForCreate);
    }

    @Test
    void validatePostForCreate_ShouldThrowException_WhenTitleIsBlank() {
        Post post = new Post(
                "",
                "Valid text",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L,
                "John",
                "Eindhoven",
                null
        );

        assertThrows(RuntimeException.class, post::validatePostForCreate);
    }

    @Test
    void validatePostForCreate_ShouldThrowException_WhenTextAndImageAreMissing() {
        Post post = new Post(
                "Valid title",
                "",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L,
                "John",
                "Eindhoven",
                null
        );

        assertThrows(RuntimeException.class, post::validatePostForCreate);
    }

    @Test
    void validatePostForCreate_ShouldPass_WhenImageExistsAndTextIsEmpty() {
        Post post = new Post(
                "Image post",
                "",
                LocalDateTime.now(),
                LocalDateTime.now(),
                1L,
                "John",
                "Eindhoven",
                "/uploads/posts/test.png"
        );

        assertDoesNotThrow(post::validatePostForCreate);
    }
}