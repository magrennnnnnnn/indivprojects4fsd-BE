package com.prolink.prolink.service;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.repository.UserRepository;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.UpdateProfileRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository,UserRepository userRepository) {
        this.profileRepository=profileRepository;
        this.userRepository=userRepository;
    }

    public Profile createProfile(CreateProfileRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (profileRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new RuntimeException("Profile already exists for this user");
        }

        Profile profile = new Profile(
                request.getName(),
                request.getLocation(),
                request.getPersonalDetails(),
                user.getId()
        );

        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile updateProfile(Long profileId, UpdateProfileRequest request) {
        Profile existingProfile = profileRepository.findByIdProfile(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setName(request.getName());
        existingProfile.setLocation(request.getLocation());
        existingProfile.setPersonalDetails(request.getPersonalDetails());

        return profileRepository.save(existingProfile);
    }
}