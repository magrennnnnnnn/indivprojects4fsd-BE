package com.prolink.prolink.repository;


import com.prolink.prolink.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkRepo extends JpaRepository<WorkExperience, Long> {
    Optional<WorkExperience> findByWorkInstitutionName(String workInstitutionName);
}
