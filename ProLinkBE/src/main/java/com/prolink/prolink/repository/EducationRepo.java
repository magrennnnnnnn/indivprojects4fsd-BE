package com.prolink.prolink.repository;

import com.prolink.prolink.entity.EducationalExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationRepo extends JpaRepository<EducationalExperience, Long>{
    Optional<EducationalExperience> findByInstitutionName(String institutionName);
}
