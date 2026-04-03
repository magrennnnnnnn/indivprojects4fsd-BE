package com.prolink.prolink.dto;

import com.prolink.prolink.enums.DegreeType;

import java.time.LocalDate;

public class AddEducationRequest {
    private String institutionName;
    private LocalDate startDateSchool;
    private LocalDate endDateSchool;
    private boolean onGoingSchool;
    private String educationalSkills;
    private DegreeType degree;
    private Long profileId;

    public AddEducationRequest(){}

    public String getInstitutionName(){return institutionName;}

    public LocalDate getStartDateSchool(){return startDateSchool;}

    public LocalDate getEndDateSchool(){return endDateSchool;}

    public boolean isOnGoingSchool(){return onGoingSchool;}

    public String getEducationalSkills(){return educationalSkills;}

    public DegreeType getDegree(){return degree;}

    public Long getProfileId(){return profileId;}

    public void setInstitutionName(String institutionName){this.institutionName=institutionName;}

    public void setStartDateSchool(LocalDate startDateSchool){this.startDateSchool=startDateSchool;}

    public void setEndDateSchool(LocalDate endDateSchool){this.endDateSchool=endDateSchool;}

    public void setEducationalSkills(String educationalSkills){this.educationalSkills=educationalSkills;}

    public void setOnGoingSchool(boolean onGoingSchool){this.onGoingSchool=onGoingSchool;}

    public void setDegree(DegreeType degree){this.degree=degree;}

    public void setProfileId(Long profileId){this.profileId=profileId;}
}
