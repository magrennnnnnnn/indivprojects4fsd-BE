package com.prolink.prolink.service;

import com.prolink.prolink.dto.AddCourseRequest;
import com.prolink.prolink.entity.Courses;
import com.prolink.prolink.entity.ProfileEntity;
import com.prolink.prolink.repository.CourseRepo;
import com.prolink.prolink.repository.ProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepo courseRepository;
    private final ProfileRepo profileRepository;

    public CourseService(CourseRepo courseRepository, ProfileRepo profileRepository){
        this.courseRepository=courseRepository;
        this.profileRepository=profileRepository;
    }

    public Courses addCourseExperience(AddCourseRequest request) {
        ProfileEntity profileEntity = profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new RuntimeException("ProfileEntity not found"));

        Courses courses = new Courses(
                request.getCourseName(),
                request.getStartDateCourse(),
                request.getEndDateCourse(),
                request.getCourseSkills(),
                request.getCourse(),
                profileEntity
        );

        return courseRepository.save(courses);
    }
}
