package com.prolink.prolink.controller;

import com.prolink.prolink.config.SessionService;
import com.prolink.prolink.dto.CompanyFollowResponse;
import com.prolink.prolink.service.CompanyFollowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/company-follows")
public class CompanyFollowController {
    private final CompanyFollowService companyFollowService;
    private final SessionService sessionService;

    public CompanyFollowController(
            CompanyFollowService companyFollowService,
            SessionService sessionService
    ) {
        this.companyFollowService = companyFollowService;
        this.sessionService = sessionService;
    }

    @PostMapping("/{companyProfileId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyFollowResponse followCompany(
            @PathVariable Long companyProfileId,
            HttpSession session
    ) {
        Long userId = getLoggedInUserId(session);

        return companyFollowService.followCompany(userId, companyProfileId);
    }

    @DeleteMapping("/{companyProfileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollowCompany(
            @PathVariable Long companyProfileId,
            HttpSession session
    ) {
        Long userId = getLoggedInUserId(session);

        companyFollowService.unfollowCompany(userId, companyProfileId);
    }

    @GetMapping("/me/company-ids")
    public List<Long> getMyFollowedCompanyIds(HttpSession session) {
        Long userId = getLoggedInUserId(session);

        return companyFollowService.getMyFollowedCompanyIds(userId);
    }

    @GetMapping("/me")
    public List<CompanyFollowResponse> getMyFollowedCompanies(HttpSession session) {
        Long userId = getLoggedInUserId(session);

        return companyFollowService.getMyFollowedCompanies(userId);
    }

    @GetMapping("/company/{companyProfileId}/followers")
    public List<CompanyFollowResponse> getCompanyFollowers(
            @PathVariable Long companyProfileId
    ) {
        return companyFollowService.getCompanyFollowers(companyProfileId);
    }

    @GetMapping("/{companyProfileId}/is-following")
    public boolean isFollowingCompany(
            @PathVariable Long companyProfileId,
            HttpSession session
    ) {
        Long userId = getLoggedInUserId(session);

        return companyFollowService.isFollowingCompany(userId, companyProfileId);
    }

    private Long getLoggedInUserId(HttpSession session) {
        Long userId = sessionService.getUserId(session);

        if (userId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Not logged in"
            );
        }

        return userId;
    }

}
