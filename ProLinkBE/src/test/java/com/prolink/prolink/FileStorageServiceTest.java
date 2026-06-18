package com.prolink.prolink;

import com.prolink.prolink.service.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.server.ResponseStatusException;



import static org.junit.jupiter.api.Assertions.*;

class FileStorageServiceTest {

    private FileStorageService fileStorageService;

    @BeforeEach
    void setUp() {
        fileStorageService = new FileStorageService();
    }

    @Test
    void savePostImage_ShouldReturnNull_WhenImageIsNull() {
        String result = fileStorageService.savePostImage(null);

        assertNull(result);
    }

    @Test
    void savePostImage_ShouldReturnNull_WhenImageIsEmpty() {
        MockMultipartFile image = new MockMultipartFile(
                "image",
                "empty.png",
                "image/png",
                new byte[0]
        );

        String result = fileStorageService.savePostImage(image);

        assertNull(result);
    }

    @Test
    void savePostImage_ShouldSaveImage_WhenImageIsValid() {
        MockMultipartFile image = new MockMultipartFile(
                "image",
                "test.png",
                "image/png",
                "fake image content".getBytes()
        );

        String result = fileStorageService.savePostImage(image);

        assertNotNull(result);
        assertTrue(result.startsWith("/uploads/posts/"));
        assertTrue(result.endsWith(".png"));
    }

    @Test
    void savePostImage_ShouldThrowException_WhenFileIsNotImage() {
        MockMultipartFile file = new MockMultipartFile(
                "image",
                "test.txt",
                "text/plain",
                "not an image".getBytes()
        );

        assertThrows(
                ResponseStatusException.class,
                () -> fileStorageService.savePostImage(file)
        );
    }

    @Test
    void savePostImage_ShouldThrowException_WhenImageIsTooLarge() {
        byte[] largeContent = new byte[6 * 1024 * 1024];

        MockMultipartFile image = new MockMultipartFile(
                "image",
                "large.png",
                "image/png",
                largeContent
        );

        assertThrows(
                ResponseStatusException.class,
                () -> fileStorageService.savePostImage(image)
        );
    }
}