package com.prolink.prolink.repository;

import com.prolink.prolink.entity.Courses;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Courses,Long> {
    Optional<Courses> findByCourseName( String courseName);
}
