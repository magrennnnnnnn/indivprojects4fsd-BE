package com.prolink.prolink.service;


import com.prolink.prolink.domain.Education;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.UpdateEducationRequest;
import com.prolink.prolink.repository.EducationRepository;
import com.prolink.prolink.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.prolink.prolink.dto.AddEducationRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;

    public EducationService(EducationRepository educationRepository,ProfileRepository profileRepository) {
       this.educationRepository=educationRepository;
       this.profileRepository=profileRepository;
    }

   public Education addEducationalExperience(Long profileId,AddEducationRequest request){
        Profile profile = profileRepository.findByIdProfile(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
        Education education = new Education(
                request.getInstitutionName(),
                request.getStartDateSchool(),
                request.getEndDateSchool(),
                request.isOnGoingSchool(),
                request.getEducationalSkills(),
                request.getDegree(),
                profile.getIdProfile()
        );
        return educationRepository.save(education);
   }

   public Education updateEducation(Long educationId, UpdateEducationRequest request){
        Education existingEducation = educationRepository.findByIdProfileEducation(educationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found"));

        existingEducation.setInstitutionName(request.getInstitutionName());
        existingEducation.setStartDateSchool(request.getStartDateSchool());
        existingEducation.setEndDateSchool(request.getEndDateSchool());
        existingEducation.setOnGoingSchool(request.isOnGoingSchool());
        existingEducation.setEducationalSkills(request.getEducationalSkills());
        existingEducation.setDegree(request.getDegree());

        return educationRepository.save(existingEducation);
   }

    public List<Education> getAllEducationByProfileId(Long profileId) {
        profileRepository.findByIdProfile(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return educationRepository.findByProfileId(profileId);
    }
}
