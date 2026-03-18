package com.prolink.prolink.entity;

import com.prolink.prolink.enums.DegreeType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "educations")

public class EducationalExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfileEducation;
    private String instituionName;
    private LocalDate startDateSchool;
    private LocalDate endDateSchool;
    private boolean OnGoingSchool;

    @Column(length = 2000)
    private String educationalSkills;

    @Enumerated(EnumType.STRING)
    private DegreeType degree;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    public EducationalExperience(){}
    public EducationalExperience(String instituionName,LocalDate startDateSchool,LocalDate endDateSchool,String educationalSkills,Profile profile,DegreeType degree,boolean onGoingSchool){
     this.instituionName=instituionName;
     this.startDateSchool=startDateSchool;
     this.endDateSchool=endDateSchool;
     this.educationalSkills=educationalSkills;
     this.profile=profile;
     this.degree=degree;
    }

    public Long getIdProfileEducation(){return idProfileEducation;}

    public String getInstituionName(){return instituionName;}

    public LocalDate getStartDateSchool(){return startDateSchool;}

    public LocalDate getEndDateSchool(){return endDateSchool;}

    public String getEducationalSkills(){return educationalSkills;}

    public DegreeType getDegree(){return degree;}

    public Profile getProfile(){return profile;}


    public void setIdProfileEducation(Long idProfileEducation){this.idProfileEducation=idProfileEducation;}

    public void setInstituionName(String instituionName){this.instituionName=instituionName;}

    public void setStartDateSchool(LocalDate startDateSchool){this.startDateSchool=startDateSchool;}

    public void setEndDateSchool(LocalDate endDateSchool){this.endDateSchool=endDateSchool;}

    public void setEducationalSkills(String educationalSkills){this.educationalSkills=educationalSkills;}

    public void setDegree(DegreeType degree){this.degree=degree;}

    public void setProfile(Profile profile){this.profile=profile;}
}
