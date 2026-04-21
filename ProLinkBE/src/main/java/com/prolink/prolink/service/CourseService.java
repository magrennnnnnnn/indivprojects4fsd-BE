package com.prolink.prolink.service;

import com.prolink.prolink.dto.AddCourseRequest;
import com.prolink.prolink.entity.CoursesEntity;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.repository.CourseJpaRepo;
import com.prolink.prolink.repository.ProfileJpaRepo;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseJpaRepo courseJpaRepository;
    private final ProfileJpaRepo profileJpaRepository;

    public CourseService(CourseJpaRepo courseJpaRepository, ProfileJpaRepo profileJpaRepository){
        this.courseJpaRepository = courseJpaRepository;
        this.profileJpaRepository = profileJpaRepository;
    }

    public CoursesEntity addCourseExperience(AddCourseRequest request) {
        ProfileEntity profileEntity = profileJpaRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("ProfileEntity not found"));

        CoursesEntity coursesEntity = new CoursesEntity(
                request.getCourseName(),
                request.getStartDateCourse(),
                request.getEndDateCourse(),
                request.getCourseSkills(),
                request.getCourse(),
                profileEntity
        );

        return courseJpaRepository.save(coursesEntity);
    }
}
