package com.prolink.prolink.service;

import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.domain.Work;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.entity.WorkExperience;
import com.prolink.prolink.dto.AddWorkRequest;
import com.prolink.prolink.repository.ProfileJpaRepo;
import com.prolink.prolink.repository.ProfileRepository;
import com.prolink.prolink.repository.WorkJpaRepo;
import com.prolink.prolink.repository.WorkRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final ProfileRepository profileRepository;

    public WorkService(WorkRepository workRepository, ProfileRepository profileRepository) {
        this.workRepository = workRepository;
        this.profileRepository = profileRepository;
    }

    public Work addWorkExperience(Long profileId, AddWorkRequest request) {
        Profile profile = profileRepository.findByIdProfile(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Work work = new Work(
                request.getWorkInstitutionName(),
                request.getStartDateWork(),
                request.getEndDateWork(),
                request.isOnGoingWork(),
                request.getWorkSkills(),
                request.getWork(),
                request.getWorkLocation(),
                request.getWorkScheduleType(),
                profile.getIdProfile()
        );

        work.validateForAddWork();

        return workRepository.save(work);
    }
}

