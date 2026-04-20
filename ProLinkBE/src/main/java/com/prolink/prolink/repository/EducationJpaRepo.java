package com.prolink.prolink.repository;

import com.prolink.prolink.entity.EducationalExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationJpaRepo extends JpaRepository<EducationalExperience, Long>{
    Optional<EducationalExperience> findByIdProfileEducation(Long idProfileEducation);
    List<EducationalExperience> findByProfileEntity_IdProfile(Long profileId);
}
