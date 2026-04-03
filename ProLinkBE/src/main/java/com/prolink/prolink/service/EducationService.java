package com.prolink.prolink.service;


import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.repository.EducationRepo;
import com.prolink.prolink.repository.ProfileRepo;
import org.springframework.stereotype.Service;
import com.prolink.prolink.entity.EducationalExperience;
import com.prolink.prolink.dto.AddEducationRequest;

@Service
public class EducationService {
    private final EducationRepo educationRepository;
    private final ProfileRepo profileRepository;

    public EducationService(EducationRepo educationRepository, ProfileRepo profileRepository) {
        this.educationRepository=educationRepository;
        this.profileRepository = profileRepository;
    }

    public EducationalExperience addEducation(AddEducationRequest request) {

        ProfileEntity profileEntity = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("ProfileEntity not found"));

        EducationalExperience educationalExperience = new EducationalExperience(
                request.getInstitutionName(),
                request.getStartDateSchool(),
                request.getEndDateSchool(),
                request.getEducationalSkills(),
                profileEntity,
                request.getDegree(),
                request.isOnGoingSchool()
        );

        return educationRepository.save(educationalExperience);
    }

}
