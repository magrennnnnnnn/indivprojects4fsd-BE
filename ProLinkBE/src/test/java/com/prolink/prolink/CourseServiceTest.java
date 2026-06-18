package com.prolink.prolink;

import com.prolink.prolink.service.CourseService;
import com.prolink.prolink.domain.Course;
import com.prolink.prolink.domain.Profile;
import com.prolink.prolink.dto.AddCourseRequest;
import com.prolink.prolink.dto.UpdateCourseRequest;
import com.prolink.prolink.enums.CoursesType;
import com.prolink.prolink.repository.CourseRepository;
import com.prolink.prolink.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void addCourse_ShouldCreateCourse_WhenProfileExists() {
        Long profileId = 1L;

        AddCourseRequest request = new AddCourseRequest();
        request.setProfileId(profileId);
        request.setCourseName("Java Backend Development");
        request.setStartDateCourse(LocalDate.of(2024, 1, 1));
        request.setEndDateCourse(LocalDate.of(2024, 5, 1));
        request.setCourseSkills("Spring Boot and REST APIs");
        request.setCourse(CoursesType.SOFTWARE_ENGINEERING);

        Profile profile = new Profile(
                profileId,
                "John Doe",
                "Eindhoven",
                "Software developer student",
                1L
        );

        Course savedCourse = new Course(
                1L,
                "Java Backend Development",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 5, 1),
                "Spring Boot and REST APIs",
                CoursesType.SOFTWARE_ENGINEERING,
                profileId
        );

        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.of(profile));
        when(courseRepository.save(any(Course.class))).thenReturn(savedCourse);

        Course result = courseService.addCourseExperience(profileId, request);

        assertEquals("Java Backend Development", result.getCourseName());
        assertEquals("Spring Boot and REST APIs", result.getCourseSkills());
        assertEquals(CoursesType.SOFTWARE_ENGINEERING, result.getCourse());

        verify(profileRepository).findByIdProfile(profileId);
        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void addCourse_ShouldThrowException_WhenProfileDoesNotExist() {
        Long profileId = 1L;

        AddCourseRequest request = new AddCourseRequest();
        request.setProfileId(profileId);
        request.setCourseName("Java Backend Development");
        request.setStartDateCourse(LocalDate.of(2024, 1, 1));
        request.setEndDateCourse(LocalDate.of(2024, 5, 1));
        request.setCourseSkills("Spring Boot and REST APIs");
        request.setCourse(CoursesType.SOFTWARE_ENGINEERING);

        when(profileRepository.findByIdProfile(profileId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> courseService.addCourseExperience(profileId, request)
        );

        verify(profileRepository).findByIdProfile(profileId);
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void updateCourse_ShouldUpdateCourse_WhenCourseExists() {
        Long courseId = 1L;
        Long profileId = 1L;

        UpdateCourseRequest request = new UpdateCourseRequest();
        request.setCourseName("Advanced Java Backend");
        request.setStartDateCourse(LocalDate.of(2024, 2, 1));
        request.setEndDateCourse(LocalDate.of(2024, 6, 1));
        request.setCourseSkills("Spring Security and JPA");
        request.setCourse(CoursesType.COMPUTER_SCIENCE);

        Course existingCourse = new Course(
                courseId,
                "Java Backend Development",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 5, 1),
                "Spring Boot and REST APIs",
                CoursesType.SOFTWARE_ENGINEERING,
                profileId
        );

        Course updatedCourse = new Course(
                courseId,
                "Advanced Java Backend",
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 6, 1),
                "Spring Security and JPA",
                CoursesType.COMPUTER_SCIENCE,
                profileId
        );

        when(courseRepository.findByIdProfileCourse(courseId)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);

        Course result = courseService.updateCourse(courseId, request);

        assertEquals("Advanced Java Backend", result.getCourseName());
        assertEquals("Spring Security and JPA", result.getCourseSkills());
        assertEquals(CoursesType.COMPUTER_SCIENCE, result.getCourse());

        verify(courseRepository).findByIdProfileCourse(courseId);
        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void updateCourse_ShouldThrowException_WhenCourseDoesNotExist() {
        Long courseId = 1L;

        UpdateCourseRequest request = new UpdateCourseRequest();

        when(courseRepository.findByIdProfileCourse(courseId)).thenReturn(Optional.empty());

        assertThrows(
                ResponseStatusException.class,
                () -> courseService.updateCourse(courseId, request)
        );

        verify(courseRepository).findByIdProfileCourse(courseId);
        verify(courseRepository, never()).save(any(Course.class));
    }
}