package com.prolink.prolink.controller;

import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.service.ProfileService;
import  com.prolink.prolink.dto.UpdateProfileRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PostMapping
    public Profile createProfile(@RequestBody CreateProfileRequest request) {
        return profileService.createProfile(request);
    }

    @GetMapping("/user/{userId}")
    public Profile getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId);
    }

    @PutMapping("/{profileId}")
    public Profile updateProfile(@PathVariable Long profileId,
                                 @RequestBody UpdateProfileRequest request) {
        return profileService.updateProfile(profileId, request);
    }

}