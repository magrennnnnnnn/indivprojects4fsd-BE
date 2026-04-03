package com.prolink.prolink.controller;

import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.service.ProfileService;
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

}