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
    private String institutionName;
    private LocalDate startDateSchool;
    private LocalDate endDateSchool;
    private boolean onGoingSchool;

    @Column(length = 2000)
    private String educationalSkills;

    @Enumerated(EnumType.STRING)
    private DegreeType degree;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private ProfileEntity profileEntity;

    public EducationalExperience(){}
    public EducationalExperience(String institutionName, LocalDate startDateSchool, LocalDate endDateSchool, String educationalSkills, ProfileEntity profileEntity, DegreeType degree, boolean onGoingSchool){
     this.institutionName=institutionName;
     this.startDateSchool=startDateSchool;
     this.endDateSchool=endDateSchool;
     this.educationalSkills=educationalSkills;
     this.profileEntity = profileEntity;
     this.degree=degree;
     this.onGoingSchool=onGoingSchool;
    }

    public Long getIdProfileEducation(){return idProfileEducation;}

    public String getInstitutionName(){return institutionName;}

    public LocalDate getStartDateSchool(){return startDateSchool;}

    public LocalDate getEndDateSchool(){return endDateSchool;}

    public String getEducationalSkills(){return educationalSkills;}

    public DegreeType getDegree(){return degree;}

    public ProfileEntity getProfile(){return profileEntity;}

    public boolean isOnGoingSchool(){return onGoingSchool;}


    public void setIdProfileEducation(Long idProfileEducation){this.idProfileEducation=idProfileEducation;}

    public void setInstitutionName(String institutionName){this.institutionName=institutionName;}

    public void setStartDateSchool(LocalDate startDateSchool){this.startDateSchool=startDateSchool;}

    public void setEndDateSchool(LocalDate endDateSchool){this.endDateSchool=endDateSchool;}

    public void setEducationalSkills(String educationalSkills){this.educationalSkills=educationalSkills;}

    public void setDegree(DegreeType degree){this.degree=degree;}

    public void setProfile(ProfileEntity profileEntity){this.profileEntity = profileEntity;}

    public void setOnGoingSchool(boolean onGoingSchool){this.onGoingSchool=onGoingSchool;}
}
