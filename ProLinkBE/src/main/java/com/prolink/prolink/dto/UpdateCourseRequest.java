package com.prolink.prolink.dto;

import com.prolink.prolink.enums.CoursesType;

import java.time.LocalDate;

public class UpdateCourseRequest {
    private String courseName;
    private LocalDate startDateCourse;
    private LocalDate endDateCourse;
    private String courseSkills;
    private CoursesType course;
    private Long profileId;

    public UpdateCourseRequest(){}

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
