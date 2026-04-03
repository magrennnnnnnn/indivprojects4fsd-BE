package com.prolink.prolink.service;


import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.repository.EducationRepo;
import com.prolink.prolink.repository.ProfileJpaRepo;
import org.springframework.stereotype.Service;
import com.prolink.prolink.entity.EducationalExperience;
import com.prolink.prolink.dto.AddEducationRequest;

@Service
public class EducationService {
    private final EducationRepo educationRepository;
    private final ProfileJpaRepo profileJpaRepository;

    public EducationService(EducationRepo educationRepository, ProfileJpaRepo profileJpaRepository) {
        this.educationRepository=educationRepository;
        this.profileJpaRepository = profileJpaRepository;
    }

    public EducationalExperience addEducation(AddEducationRequest request) {

        ProfileEntity profileEntity = profileJpaRepository.findById(request.getProfileId())
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
