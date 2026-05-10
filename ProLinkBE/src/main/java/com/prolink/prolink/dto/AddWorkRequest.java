package com.prolink.prolink.dto;

import com.prolink.prolink.enums.WorkLocation;
import com.prolink.prolink.enums.WorkScheduleType;
import com.prolink.prolink.enums.WorkType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AddWorkRequest {

    @NotNull(message = "Profile id is required")
    @Positive(message = "Profile id must be positive")
    private Long profileId;

    @NotBlank(message = "Work institution name is required")
    @Size(min = 2, max = 150, message = "Work institution name must be between 2 and 150 characters")
    private String workInstitutionName;

    @NotNull(message = "Work start date is required")
    private LocalDate startDateWork;

    private LocalDate endDateWork;

    private boolean onGoingWork;

    @NotBlank(message = "Work skills are required")
    @Size(min = 2, max = 2000, message = "Work skills must be between 2 and 2000 characters")
    private String workSkills;

    @NotNull(message = "Work type is required")
    private WorkType work;

    @NotNull(message = "Work location is required")
    private WorkLocation workLocation;

    @NotNull(message = "Work schedule type is required")
    private WorkScheduleType workScheduleType;

    public AddWorkRequest(){}

    public Long getProfileId(){return profileId;}

    public String getWorkInstitutionName(){return workInstitutionName;}

    public LocalDate getStartDateWork(){return startDateWork;}

    public LocalDate getEndDateWork(){return endDateWork;}

    public boolean isOnGoingWork(){return onGoingWork;}

    public String getWorkSkills(){return workSkills;}

    public WorkType getWork(){return work;}

    public WorkLocation getWorkLocation(){return workLocation;}

    public WorkScheduleType getWorkScheduleType(){return workScheduleType;}

    public void setProfileId(Long profileId){this.profileId = profileId;}

    public void setWorkInstitutionName(String workInstitutionName){this.workInstitutionName = workInstitutionName;}

    public void setStartDateWork(LocalDate startDateWork){this.startDateWork = startDateWork;}

    public void setEndDateWork(LocalDate endDateWork){this.endDateWork = endDateWork;}

    public void setOnGoingWork(boolean onGoingWork){this.onGoingWork = onGoingWork;}

    public void setWorkSkills(String workSkills){this.workSkills = workSkills;}

    public void setWork(WorkType work){this.work = work;}

    public void setWorkLocation(WorkLocation workLocation){this.workLocation = workLocation;}

    public void setWorkScheduleType(WorkScheduleType workScheduleType){this.workScheduleType = workScheduleType;}

    @AssertTrue(message = "End date is required when work is not ongoing")
    public boolean isEndDateValidForWorkStatus() {
        return onGoingWork || endDateWork != null;
    }

    @AssertTrue(message = "End date must be after start date")
    public boolean isWorkDateRangeValid() {
        if (startDateWork == null || endDateWork == null) {
            return true;
        }

        return !endDateWork.isBefore(startDateWork);
    }
}