package com.prolink.prolink.controller;

import com.prolink.prolink.dto.CreateProfileRequest;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.service.ProfileService;
import com.prolink.prolink.config.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import  com.prolink.prolink.dto.UpdateProfileRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final SessionService sessionService;

    public ProfileController(ProfileService profileService, SessionService sessionService) {
        this.profileService = profileService;
        this.sessionService=sessionService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile createProfile(@RequestBody CreateProfileRequest request,HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return profileService.createProfile(userId, request);
    }

    @GetMapping("/me")
    public Profile getMyProfile(HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return profileService.getProfileByUserId(userId);
    }

    @GetMapping("/user/{userId}")
    public Profile getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId);
    }

    @PutMapping("/me")
    public Profile updateProfile(@RequestBody UpdateProfileRequest request, HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
        }

        return profileService.updateProfileByUserId(userId, request);
    }

}