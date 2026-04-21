package com.prolink.prolink.repository;

import com.prolink.prolink.entity.CoursesEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseJpaRepo extends JpaRepository<CoursesEntity,Long> {
    Optional<CoursesEntity> findByIdProfileCourse(Long idProfileCourse);
    List<CoursesEntity> findByProfileEntity_IdProfile(Long profileId);
}
