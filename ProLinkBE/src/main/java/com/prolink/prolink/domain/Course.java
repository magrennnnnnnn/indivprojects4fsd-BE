package com.prolink.prolink.domain;

import com.prolink.prolink.enums.CoursesType;

import java.time.LocalDate;

public class Course {
    private Long idProfileCourse;
    private String courseName;
    private LocalDate startDateCourse;
    private LocalDate endDateCourse;
    private String courseSkills;
    private CoursesType course;
    private Long profileId;

    public Course(Long idProfileCourse,String courseName,LocalDate startDateCourse,LocalDate endDateCourse,String courseSkills,CoursesType course,Long profileId){
        this.idProfileCourse=idProfileCourse;
        this.courseName=courseName;
        this.startDateCourse=startDateCourse;
        this.endDateCourse=endDateCourse;
        this.courseSkills=courseSkills;
        this.course=course;
        this.profileId=profileId;
    }

    public Course(String courseName,LocalDate startDateCourse,LocalDate endDateCourse,String courseSkills,CoursesType course,Long profileId){
        this.courseName=courseName;
        this.startDateCourse=startDateCourse;
        this.endDateCourse=endDateCourse;
        this.courseSkills=courseSkills;
        this.course=course;
        this.profileId=profileId;
    }

    public Long getIdProfileCourse(){return idProfileCourse;}
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
