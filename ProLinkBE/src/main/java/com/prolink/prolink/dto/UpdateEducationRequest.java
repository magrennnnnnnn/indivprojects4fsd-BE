package com.prolink.prolink.dto;

import com.prolink.prolink.enums.DegreeType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UpdateEducationRequest {
    @NotBlank(message = "Institution name is required")
    @Size(min = 2, max = 150, message = "Institution name must be between 2 and 150 characters")
    private String institutionName;

    @NotNull(message = "School start date is required")
    private LocalDate startDateSchool;

    private LocalDate endDateSchool;

    private boolean onGoingSchool;

    @NotBlank(message = "Educational skills are required")
    @Size(min = 2, max = 2000, message = "Educational skills must be between 2 and 2000 characters")
    private String educationalSkills;

    @NotNull(message = "Degree type is required")
    private DegreeType degree;

    @NotNull(message = "Profile id is required")
    @Positive(message = "Profile id must be positive")
    private Long profileId;


    public UpdateEducationRequest(){}

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

    @AssertTrue(message = "End date is required when school is not ongoing")
    public boolean isEndDateValidForSchoolStatus() {
        return onGoingSchool || endDateSchool != null;
    }

    @AssertTrue(message = "End date must be after start date")
    public boolean isSchoolDateRangeValid() {
        if (startDateSchool == null || endDateSchool == null) {
            return true;
        }

        return !endDateSchool.isBefore(startDateSchool);
    }
}
