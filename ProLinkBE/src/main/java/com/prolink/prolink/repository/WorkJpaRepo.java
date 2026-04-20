package com.prolink.prolink.repository;


import com.prolink.prolink.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkJpaRepo extends JpaRepository<WorkExperience, Long> {
    Optional<WorkExperience> findByIdProfileWork(Long idProfileWork);
    List<WorkExperience> findByProfileEntity_IdProfile(Long profileId);
}
