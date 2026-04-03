package com.prolink.prolink.service;

import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.entity.WorkExperience;
import com.prolink.prolink.dto.AddWorkRequest;
import com.prolink.prolink.repository.ProfileJpaRepo;
import com.prolink.prolink.repository.WorkRepo;
import org.springframework.stereotype.Service;


@Service
public class WorkService {

    private final WorkRepo workRepository;
    private final ProfileJpaRepo profileJpaRepository;

    public WorkService(WorkRepo workRepository, ProfileJpaRepo profileJpaRepository) {
        this.workRepository = workRepository;
        this.profileJpaRepository = profileJpaRepository;
    }

    public WorkExperience addWorkExperience(AddWorkRequest request) {

        ProfileEntity profileEntity = profileJpaRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("ProfileEntity not found"));

        WorkExperience workExperience = new WorkExperience(
                request.getWorkInstitutionName(),
                request.getWorkSkills(),
                request.getStartDateWork(),
                request.getEndDateWork(),
                profileEntity,
                request.getWork(),
                request.isOnGoingWork(),
                request.getWorkLocation(),
                request.getWorkScheduleType()
        );

        return workRepository.save(workExperience);
    }
}
