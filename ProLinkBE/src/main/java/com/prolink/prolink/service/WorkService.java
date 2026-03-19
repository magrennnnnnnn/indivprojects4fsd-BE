package com.prolink.prolink.service;

import com.prolink.prolink.dto.AddEducationRequest;
import com.prolink.prolink.entity.EducationalExperience;
import com.prolink.prolink.entity.Profile;
import com.prolink.prolink.entity.WorkExperience;
import com.prolink.prolink.enums.WorkLocation;
import com.prolink.prolink.dto.AddWorkRequest;
import com.prolink.prolink.entity.Profile;
import com.prolink.prolink.entity.WorkExperience;
import com.prolink.prolink.repository.ProfileRepo;
import com.prolink.prolink.repository.WorkRepo;
import org.springframework.stereotype.Service;


@Service
public class WorkService {

    private final WorkRepo workRepository;
    private final ProfileRepo profileRepository;

    public WorkService(WorkRepo workRepository, ProfileRepo profileRepository) {
        this.workRepository = workRepository;
        this.profileRepository = profileRepository;
    }

    public WorkExperience addWorkExperience(AddWorkRequest request) {

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        WorkExperience workExperience = new WorkExperience(
                request.getWorkInstitutionName(),
                request.getWorkSkills(),
                request.getStartDateWork(),
                request.getEndDateWork(),
                profile,
                request.getWork(),
                request.isOnGoingWork(),
                request.getWorkLocation(),
                request.getWorkScheduleType()
        );

        return workRepository.save(workExperience);
    }
}
