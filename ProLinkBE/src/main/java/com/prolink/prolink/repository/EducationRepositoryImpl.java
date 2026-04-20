package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Education;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.entity.EducationalExperience;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EducationRepositoryImpl implements EducationRepository{
    private final EducationJpaRepo educationJpaRepo;

    public EducationRepositoryImpl(EducationJpaRepo educationJpaRepo){
        this.educationJpaRepo=educationJpaRepo;
    }

    @Override
    public Optional<Education> findByIdProfileEducation(Long idProfileEducation){
        return educationJpaRepo.findByIdProfileEducation(idProfileEducation)
                .map(this::toDomain);
    }

    @Override
    public List<Education> findByProfileId(Long profileId){
        return educationJpaRepo.findByProfileEntity_IdProfile(profileId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Education save(Education education){
        EducationalExperience entity = toEntity(education);
        EducationalExperience saved = educationJpaRepo.save(entity);
        return toDomain(saved);
    }

    private Education toDomain(EducationalExperience entity){
        return new Education(
                entity.getIdProfileEducation(),
                entity.getInstitutionName(),
                entity.getStartDateSchool(),
                entity.getEndDateSchool(),
                entity.isOnGoingSchool(),
                entity.getEducationalSkills(),
                entity.getDegree(),
                entity.getProfile().getId()
        );
    }

    private EducationalExperience toEntity(Education education){
        EducationalExperience entity = new EducationalExperience();
        entity.setIdProfileEducation(education.getIdProfileEducation());
        entity.setInstitutionName(education.getInstitutionName());
        entity.setStartDateSchool(education.getStartDateSchool());
        entity.setEndDateSchool(education.getEndDateSchool());
        entity.setOnGoingSchool(education.isOnGoingSchool());
        entity.setEducationalSkills(education.getEducationalSkills());
        entity.setDegree(education.getDegree());

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(education.getProfileId());
        entity.setProfile(profileEntity);

        return entity;
    }


}
