package com.prolink.prolink.service;

import com.prolink.prolink.domain.Course;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.AddCourseRequest;
import com.prolink.prolink.dto.UpdateCourseRequest;
import com.prolink.prolink.repository.CourseRepository;
import com.prolink.prolink.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ProfileRepository profileRepository;

    public CourseService(CourseRepository courseRepository,ProfileRepository profileRepository){
       this.courseRepository=courseRepository;
       this.profileRepository=profileRepository;
    }

    public Course addCourseExperience(Long profileId,AddCourseRequest request) {
        Profile profile = profileRepository.findByIdProfile(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Course course = new Course(
                request.getCourseName(),
                request.getStartDateCourse(),
                request.getEndDateCourse(),
                request.getCourseSkills(),
                request.getCourse(),
                profile.getIdProfile()
        );

        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, UpdateCourseRequest request){
        Course existingCourse = courseRepository.findByIdProfileCourse(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        existingCourse.setCourseName(request.getCourseName());
        existingCourse.setStartDateCourse(request.getStartDateCourse());
        existingCourse.setEndDateCourse(request.getEndDateCourse());
        existingCourse.setCourseSkills(request.getCourseSkills());
        existingCourse.setCourse(request.getCourse());

        return courseRepository.save(existingCourse);
    }

    public List<Course> getAllCoursesByProfileId(Long profileId) {
        profileRepository.findByIdProfile(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        return courseRepository.findByProfileId(profileId);
    }
}
