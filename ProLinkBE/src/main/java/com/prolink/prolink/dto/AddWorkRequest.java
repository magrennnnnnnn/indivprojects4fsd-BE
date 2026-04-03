package com.prolink.prolink.dto;

import com.prolink.prolink.enums.WorkLocation;
import com.prolink.prolink.enums.WorkScheduleType;
import com.prolink.prolink.enums.WorkType;

import java.time.LocalDate;

public class AddWorkRequest {

    private Long profileId;
    private String workInstitutionName;
    private LocalDate startDateWork;
    private LocalDate endDateWork;
    private boolean onGoingWork;
    private String workSkills;
    private WorkType work;
    private WorkLocation workLocation;
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
}