package com.prolink.prolink.service;

import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.entity.Profile;
import com.prolink.prolink.entity.UserEntity;
import com.prolink.prolink.repository.ProfileRepo;
import com.prolink.prolink.repository.UserJpaRepo;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepo profileRepository;
    private final UserJpaRepo userJpaRepository;

    public ProfileService(ProfileRepo profileRepository, UserJpaRepo userJpaRepository) {
        this.profileRepository = profileRepository;
        this.userJpaRepository = userJpaRepository;
    }
    public Profile createProfile(CreateProfileRequest request) {
        UserEntity userEntity = userJpaRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (profileRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new RuntimeException("Profile already exists for this user");
        }

        Profile profile = new Profile(
                request.getName(),
                request.getLocation(),
                request.getPersonalDetails(),
                userEntity
        );

        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}