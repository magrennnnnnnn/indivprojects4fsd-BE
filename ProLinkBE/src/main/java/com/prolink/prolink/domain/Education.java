package com.prolink.prolink.domain;

import com.prolink.prolink.enums.DegreeType;

import java.time.LocalDate;

public class Education {
    private Long idProfileEducation;
    private String institutionName;
    private LocalDate startDateSchool;
    private LocalDate endDateSchool;
    private boolean onGoingSchool;
    private String educationalSkills;
    private DegreeType degree;
    private Long profileId;

    public Education(Long idProfileEducation,String institutionName,LocalDate startDateSchool,LocalDate endDateSchool,boolean onGoingSchool,String educationalSkills,DegreeType degree,Long profileId){
        this.idProfileEducation=idProfileEducation;
        this.institutionName=institutionName;
        this.startDateSchool=startDateSchool;
        this.endDateSchool=endDateSchool;
        this.onGoingSchool=onGoingSchool;
        this.educationalSkills=educationalSkills;
        this.degree=degree;
        this.profileId=profileId;
    }

    public Education(String institutionName,LocalDate startDateSchool,LocalDate endDateSchool,boolean onGoingSchool,String educationalSkills,DegreeType degree,Long profileId){
        this.institutionName=institutionName;
        this.startDateSchool=startDateSchool;
        this.endDateSchool=endDateSchool;
        this.onGoingSchool=onGoingSchool;
        this.educationalSkills=educationalSkills;
        this.degree=degree;
        this.profileId=profileId;
    }

    public Long getIdProfileEducation(){return idProfileEducation;}
    public String getInstitutionName(){return institutionName;}
    public LocalDate getStartDateSchool(){return startDateSchool;}
    public LocalDate getEndDateSchool(){return endDateSchool;}
    public String getEducationalSkills(){return educationalSkills;}
    public boolean isOnGoingSchool(){return onGoingSchool;}
    public DegreeType getDegree(){return degree;}
    public Long getProfileId(){return profileId;}

    public void setInstitutionName(String institutionName){this.institutionName=institutionName;}
    public void setStartDateSchool(LocalDate startDateSchool){this.startDateSchool=startDateSchool;}
    public void setEndDateSchool(LocalDate endDateSchool){this.endDateSchool=endDateSchool;}
    public void setOnGoingSchool(boolean onGoingSchool){this.onGoingSchool=onGoingSchool;}
    public void setEducationalSkills(String educationalSkills){this.educationalSkills=educationalSkills;}
    public void setDegree(DegreeType degree){this.degree=degree;}
    public void setProfileId(Long profileId){this.profileId=profileId;}

    
}
