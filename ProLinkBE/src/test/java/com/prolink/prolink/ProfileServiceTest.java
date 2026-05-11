package com.prolink.prolink;

import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.dto.UpdateProfileRequest;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.repository.UserRepository;
import com.prolink.prolink.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {
    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void createProfile_ShouldCreateProfile_WhenUserExistsAndProfileDoesNotExist() {
        Long userId = 1L;

        CreateProfileRequest request = new CreateProfileRequest();
        request.setName("John Doe");
        request.setLocation("Eindhoven");
        request.setPersonalDetails("Software developer student");

        User user = new User(userId, "john@test.com", "password123");

        Profile savedProfile = new Profile(
                1L,
                "John Doe",
                "Eindhoven",
                "Software developer student",
                userId
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(profileRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(profileRepository.save(any(Profile.class))).thenReturn(savedProfile);

        Profile result = profileService.createProfile(userId, request);

        assertEquals("John Doe", result.getName());
        assertEquals("Eindhoven", result.getLocation());
        assertEquals("Software developer student", result.getPersonalDetails());
        assertEquals(userId, result.getUserId());

        verify(userRepository).findById(userId);
        verify(profileRepository).findByUserId(userId);
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void createProfile_ShouldThrowException_WhenUserDoesNotExist() {
        Long userId = 1L;

        CreateProfileRequest request = new CreateProfileRequest();
        request.setName("John Doe");
        request.setLocation("Eindhoven");
        request.setPersonalDetails("Software developer student");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> profileService.createProfile(userId, request)
        );

        verify(userRepository).findById(userId);
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    void createProfile_ShouldThrowException_WhenProfileAlreadyExists() {
        Long userId = 1L;

        CreateProfileRequest request = new CreateProfileRequest();
        request.setName("John Doe");
        request.setLocation("Eindhoven");
        request.setPersonalDetails("Software developer student");

        User user = new User(userId, "john@test.com", "password123");

        Profile existingProfile = new Profile(
                1L,
                "John Doe",
                "Eindhoven",
                "Existing profile",
                userId
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(existingProfile));

        assertThrows(
                ResponseStatusException.class,
                () -> profileService.createProfile(userId, request)
        );

        verify(userRepository).findById(userId);
        verify(profileRepository).findByUserId(userId);
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    void getProfileByUserId_ShouldReturnProfile_WhenProfileExists() {
        Long userId = 1L;

        Profile profile = new Profile(
                1L,
                "John Doe",
                "Eindhoven",
                "Software developer student",
                userId
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));

        Profile result = profileService.getProfileByUserId(userId);

        assertEquals(1L, result.getIdProfile());
        assertEquals("John Doe", result.getName());
        assertEquals(userId, result.getUserId());

        verify(profileRepository).findByUserId(userId);
    }

    @Test
    void getProfileByUserId_ShouldThrowException_WhenProfileDoesNotExist() {
        Long userId = 1L;

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> profileService.getProfileByUserId(userId)
        );

        verify(profileRepository).findByUserId(userId);
    }

    @Test
    void updateProfileByUserId_ShouldUpdateProfile_WhenProfileExists() {
        Long userId = 1L;

        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setName("Updated Name");
        request.setLocation("Amsterdam");
        request.setPersonalDetails("Updated personal details");

        Profile existingProfile = new Profile(
                1L,
                "Old Name",
                "Old Location",
                "Old details",
                userId
        );

        Profile updatedProfile = new Profile(
                1L,
                "Updated Name",
                "Amsterdam",
                "Updated personal details",
                userId
        );

        when(profileRepository.findByUserId(userId)).thenReturn(Optional.of(existingProfile));
        when(profileRepository.save(any(Profile.class))).thenReturn(updatedProfile);

        Profile result = profileService.updateProfileByUserId(userId, request);

        assertEquals("Updated Name", result.getName());
        assertEquals("Amsterdam", result.getLocation());
        assertEquals("Updated personal details", result.getPersonalDetails());

        verify(profileRepository).findByUserId(userId);
        verify(profileRepository).save(any(Profile.class));
    }
}
