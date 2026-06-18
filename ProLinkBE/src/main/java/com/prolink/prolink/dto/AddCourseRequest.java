package com.prolink.prolink.dto;

import com.prolink.prolink.enums.CoursesType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AddCourseRequest {
    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 150, message = "Course name must be between 2 and 150 characters")
    private String courseName;

    @NotNull(message = "Course start date is required")
    private LocalDate startDateCourse;

    @FutureOrPresent(message = "Course end date can not be in the past")
    private LocalDate endDateCourse;

    @NotBlank(message = "Course skills are required")
    @Size(min = 2, max = 2000, message = "Course skills must be between 2 and 2000 characters")
    private String courseSkills;

    @NotNull(message = "Course type is required")
    private CoursesType course;

    @NotNull(message = "Profile id is required")
    @Positive(message = "Profile id must be positive")
    private Long profileId;

    public AddCourseRequest(){/**/}

    public String getCourseName(){return courseName;}

    public LocalDate getStartDateCourse(){return startDateCourse;}

    public LocalDate getEndDateCourse(){return endDateCourse;}

    public String getCourseSkills(){return courseSkills;}

    public CoursesType getCourse(){return course;}

    public Long getProfileId(){return profileId;}

    public void setCourseName(String courseName){this.courseName=courseName;}

    public void setStartDateCourse(LocalDate startDateCourse){this.startDateCourse=startDateCourse;}

    public void setEndDateCourse(LocalDate endDateCourse){this.endDateCourse=endDateCourse;}

    public void setCourseSkills(String courseSkills){this.courseSkills=courseSkills;}

    public void setCourse(CoursesType course){this.course=course;}

    public void setProfileId(Long profileId){this.profileId=profileId;}

}
