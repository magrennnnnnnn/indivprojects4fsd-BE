package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Education;

import java.util.List;
import java.util.Optional;

public interface EducationRepository {
    Optional<Education> findByIdProfileEducation(Long idProfileEducation);
    List<Education> findByProfileId(Long profileId);
    Education save(Education education);
}
