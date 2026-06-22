package com.prolink.prolink.service;

import com.prolink.prolink.domain.CompanyFollow;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.dto.CompanyFollowResponse;
import com.prolink.prolink.enums.Roles;
import com.prolink.prolink.repository.CompanyFollowRepository;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompanyFollowService {
    private static final String PROFILE_NOT_FOUND = "Profile not found";
    private static final String COMPANY_PROFILE_NOT_FOUND = "Company profile not found";
    private static final String COMPANY_USER_NOT_FOUND = "Company user not found";

    private final CompanyFollowRepository companyFollowRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public CompanyFollowService(CompanyFollowRepository companyFollowRepository, ProfileRepository profileRepository, UserRepository userRepository) {
        this.companyFollowRepository = companyFollowRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public CompanyFollowResponse followCompany(Long userId, Long companyProfileId) {
        Profile followerProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        PROFILE_NOT_FOUND
                ));

        Profile companyProfile = profileRepository.findByIdProfile(companyProfileId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        COMPANY_PROFILE_NOT_FOUND
                ));

        User followerUser = userRepository.findById(followerProfile.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Follower user not found"
                ));

        User companyUser = userRepository.findById(companyProfile.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        COMPANY_USER_NOT_FOUND
                ));

        if (followerProfile.getIdProfile().equals(companyProfile.getIdProfile())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You cannot follow yourself"
            );
        }

        if (followerUser.getRoles() == Roles.COMPANY) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Company accounts cannot follow other accounts"
            );
        }

        if (companyUser.getRoles() != Roles.COMPANY) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You can only follow company accounts"
            );
        }

        return companyFollowRepository
                .findByFollowerProfileIdAndCompanyProfileId(
                        followerProfile.getIdProfile(),
                        companyProfile.getIdProfile()
                )
                .map(CompanyFollowResponse::fromDomain)
                .orElseGet(() -> {
                    CompanyFollow createdFollow = companyFollowRepository.createFollow(
                            followerProfile.getIdProfile(),
                            companyProfile.getIdProfile()
                    );

                    return CompanyFollowResponse.fromDomain(createdFollow);
                });
    }

    public void unfollowCompany(Long userId, Long companyProfileId) {
        Profile followerProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        PROFILE_NOT_FOUND
                ));

        Profile companyProfile = profileRepository.findByIdProfile(companyProfileId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        COMPANY_PROFILE_NOT_FOUND
                ));

        User companyUser = userRepository.findById(companyProfile.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        COMPANY_USER_NOT_FOUND
                ));

        if (companyUser.getRoles() != Roles.COMPANY) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "You can only unfollow company accounts"
            );
        }

        companyFollowRepository.deleteByFollowerProfileIdAndCompanyProfileId(
                followerProfile.getIdProfile(),
                companyProfile.getIdProfile()
        );
    }

    public List<Long> getMyFollowedCompanyIds(Long userId) {
        Profile followerProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        PROFILE_NOT_FOUND
                ));

        return companyFollowRepository.findByFollowerProfileId(followerProfile.getIdProfile())
                .stream()
                .map(CompanyFollow::getCompanyProfileId)
                .toList();
    }

    public List<CompanyFollowResponse> getMyFollowedCompanies(Long userId) {
        Profile followerProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        PROFILE_NOT_FOUND
                ));

        return companyFollowRepository.findByFollowerProfileId(followerProfile.getIdProfile())
                .stream()
                .map(CompanyFollowResponse::fromDomain)
                .toList();
    }

    public List<CompanyFollowResponse> getCompanyFollowers(Long companyProfileId) {
        Profile companyProfile = profileRepository.findByIdProfile(companyProfileId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        COMPANY_PROFILE_NOT_FOUND
                ));

        User companyUser = userRepository.findById(companyProfile.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        COMPANY_USER_NOT_FOUND
                ));

        if (companyUser.getRoles() != Roles.COMPANY) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This profile is not a company account"
            );
        }

        return companyFollowRepository.findByCompanyProfileId(companyProfileId)
                .stream()
                .map(CompanyFollowResponse::fromDomain)
                .toList();
    }

    public boolean isFollowingCompany(Long userId, Long companyProfileId) {
        Profile followerProfile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        PROFILE_NOT_FOUND
                ));

        return companyFollowRepository.existsByFollowerProfileIdAndCompanyProfileId(
                followerProfile.getIdProfile(),
                companyProfileId
        );
    }
}
