package com.prolink.prolink.entity;

import com.prolink.prolink.enums.CoursesType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfileCourse;
    private String courseName;
    private LocalDate startDateCourse;
    private LocalDate endDateCourse;

    @Column(length = 2000)
    private String courseSkills;

    @Enumerated(EnumType.STRING)
    private CoursesType course;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private ProfileEntity profileEntity;

    public Courses(){}
    public Courses(String courseName, LocalDate startDateCourse, LocalDate endDateCourse, String courseSkills, CoursesType course, ProfileEntity profileEntity){
        this.courseName=courseName;
        this.startDateCourse=startDateCourse;
        this.endDateCourse=endDateCourse;
        this.courseSkills=courseSkills;
        this.course=course;
        this.profileEntity = profileEntity;
    }

    public Long getIdProfileCourse(){return idProfileCourse;}

    public String getCourseName(){return courseName;}

    public String getCourseSkills(){return courseSkills;}

    public LocalDate getStartDateCourse(){return startDateCourse;}

    public LocalDate getEndDateCourse(){return endDateCourse;}

    public CoursesType getCourse(){return course;}

    public ProfileEntity getProfile(){return profileEntity;}

    public void setIdProfileCourse(Long idProfileCourse){this.idProfileCourse=idProfileCourse;}

    public void setCourseName(String courseName){this.courseName=courseName;}

    public void setCourseSkills(String courseSkills){this.courseSkills=courseSkills;}

    public void setStartDateCourse(LocalDate startDateCourse){this.startDateCourse=startDateCourse;}

    public void setEndDateCourse(LocalDate endDateCourse){this.endDateCourse=endDateCourse;}

    public void setCourse(CoursesType course){this.course=course;}

    public void setProfile(ProfileEntity profileEntity){this.profileEntity = profileEntity;}
}
