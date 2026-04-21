package com.prolink.prolink.repository;

import com.prolink.prolink.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findByIdProfileCourse(Long idProfileCourse);
    List<Course> findByProfileId(Long profileId);
    Course save(Course course);
}
