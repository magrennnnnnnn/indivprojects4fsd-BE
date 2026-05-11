package com.prolink.prolink.domain;

import com.prolink.prolink.enums.CoursesType;
import com.prolink.prolink.exceptionhandler.InvalidCourseEndDateException;
import com.prolink.prolink.exceptionhandler.InvalidCourseNameException;
import com.prolink.prolink.exceptionhandler.InvalidCourseSkillsException;
import com.prolink.prolink.exceptionhandler.InvalidCourseStartDateException;

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

    public void validateCourseForCreate(){
      validateCourseName();
      validateCourseSkills();
      validateCourseStartDate();
      validateCourseEndDate();
    }

    public void validateCourseForUpdate(){
      validateCourseName();
      validateCourseSkills();
      validateCourseStartDate();
      validateCourseEndDate();
    }

    public void validateCourseName(){
        if(courseName == null || courseName.isBlank()){
            throw new InvalidCourseNameException("Course name can not be empty!");
        }

        if(courseName.length() <2){
            throw new InvalidCourseNameException("Course name should be at least 2 characters long!");
        }
    }

    public void validateCourseSkills(){
        if(courseSkills == null || courseSkills.isBlank()){
            throw new InvalidCourseSkillsException("Course skills can not be empty!");
        }

        if(courseSkills.length() <2){
            throw new InvalidCourseSkillsException("Course skills should be at least 2 characters long!");
        }
    }

    public void validateCourseStartDate(){
        if(startDateCourse == null){
            throw new InvalidCourseStartDateException("Start date is required!");
        }
    }

    public void validateCourseEndDate(){
        if(endDateCourse == null){
            throw new InvalidCourseEndDateException("End date is required!");
        }

        if(startDateCourse != null && endDateCourse.isBefore(startDateCourse)){
            throw new InvalidCourseEndDateException("End date can not be before start date!");
        }
    }




}
