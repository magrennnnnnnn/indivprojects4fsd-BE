package com.prolink.prolink.service;

import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.entity.ProfileEntity;
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
    public ProfileEntity createProfile(CreateProfileRequest request) {
        UserEntity userEntity = userJpaRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (profileRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new RuntimeException("ProfileEntity already exists for this user");
        }

        ProfileEntity profileEntity = new ProfileEntity(
                request.getName(),
                request.getLocation(),
                request.getPersonalDetails(),
                userEntity
        );

        return profileRepository.save(profileEntity);
    }

    public ProfileEntity getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("ProfileEntity not found"));
    }
}