package com.prolink.prolink.service;

import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.entity.Profile;
import com.prolink.prolink.entity.User;
import com.prolink.prolink.repository.ProfileRepo;
import com.prolink.prolink.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepo profileRepository;
    private final UserRepo userRepository;

    public ProfileService(ProfileRepo profileRepository, UserRepo userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
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
                user
        );

        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}