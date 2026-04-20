package com.prolink.prolink.domain;

import com.prolink.prolink.enums.WorkLocation;
import com.prolink.prolink.enums.WorkScheduleType;
import com.prolink.prolink.enums.WorkType;
import com.prolink.prolink.exceptionhandler.InvalidWorkEndDateException;
import com.prolink.prolink.exceptionhandler.InvalidWorkInstitutionNameException;
import com.prolink.prolink.exceptionhandler.InvalidWorkSkillsException;
import com.prolink.prolink.exceptionhandler.InvalidWorkStartDateException;

import java.time.LocalDate;

public class Work {
    private Long idProfileWork;
    private String workInstitutionName;
    private LocalDate startDateWork;
    private LocalDate endDateWork;
    private boolean onGoingWork;
    private String workSkills;

    private WorkType work;
    private WorkLocation workLocation;
    private WorkScheduleType workScheduleType;
    private Long profileId;

    public Work(Long idProfileWork,String workInstitutionName,LocalDate startDateWork,LocalDate endDateWork,boolean onGoingWork,String workSkills,WorkType work,WorkLocation workLocation,WorkScheduleType workScheduleType,Long profileId){
        this.idProfileWork=idProfileWork;
        this.workInstitutionName=workInstitutionName;
        this.startDateWork=startDateWork;
        this.endDateWork=endDateWork;
        this.onGoingWork=onGoingWork;
        this.workSkills=workSkills;
        this.work=work;
        this.workLocation=workLocation;
        this.workScheduleType=workScheduleType;
        this.profileId=profileId;
    }

    public Work(String workInstitutionName,LocalDate startDateWork,LocalDate endDateWork,boolean onGoingWork,String workSkills,WorkType work,WorkLocation workLocation,WorkScheduleType workScheduleType,Long profileId){
        this.workInstitutionName=workInstitutionName;
        this.startDateWork=startDateWork;
        this.endDateWork=endDateWork;
        this.onGoingWork=onGoingWork;
        this.workSkills=workSkills;
        this.work=work;
        this.workLocation=workLocation;
        this.workScheduleType=workScheduleType;
        this.profileId=profileId;
    }

    public Long getIdProfileWork(){return idProfileWork;}
    public String getWorkInstitutionName(){return workInstitutionName;}
    public LocalDate getStartDateWork(){return startDateWork;}
    public LocalDate getEndDateWork(){return endDateWork;}
    public boolean isOnGoingWork(){return onGoingWork;}
    public String getWorkSkills(){return workSkills;}
    public WorkType getWork(){return work;}
    public WorkLocation getWorkLocation(){return workLocation;}
    public WorkScheduleType getWorkScheduleType(){return workScheduleType;}
    public Long getProfileId(){return profileId;}

    public void setWorkInstitutionName(String workInstitutionName){this.workInstitutionName=workInstitutionName;}
    public void setStartDateWork(LocalDate startDateWork){this.startDateWork=startDateWork;}
    public void setEndDateWork(LocalDate endDateWork){this.endDateWork=endDateWork;}
    public void setOnGoingWork(boolean onGoingWork){this.onGoingWork=onGoingWork;}
    public void setWorkSkills(String workSkills){this.workSkills=workSkills;}
    public void setWork(WorkType work){this.work=work;}
    public void setWorkLocation(WorkLocation workLocation){this.workLocation=workLocation;}
    public void setWorkScheduleType(WorkScheduleType workScheduleType){this.workScheduleType=workScheduleType;}
    public void setProfileId(Long profileId){this.profileId=profileId;}

    public void validateForAddWork(){
        validateWorkInstitutionName();
        validateWorkSkills();
        validateWorkStartDate();
        validateWorkEndDate();
    }

    public void validateForUpdateWork(){
        validateWorkInstitutionName();
        validateWorkSkills();
        validateWorkStartDate();
        validateWorkEndDate();
    }

    public void validateWorkInstitutionName(){
        if(workInstitutionName == null || workInstitutionName.isBlank()){
            throw new InvalidWorkInstitutionNameException("This field can not be empty");
        }
        if(workInstitutionName.length()<2){
            throw new InvalidWorkInstitutionNameException("The name should contain at least 2 characters");
        }
    }

    public void validateWorkSkills(){
        if(workSkills == null || workSkills.isBlank()){
            throw new InvalidWorkSkillsException("This field can not be empty");
        }

        if(workSkills.length()<2){
            throw new InvalidWorkSkillsException("This field should contain at least 2 characters");
        }
    }

    public void validateWorkStartDate() {
        if (startDateWork == null) {
            throw new InvalidWorkStartDateException("Start date is required");
        }
    }

    public void validateWorkEndDate() {
        if (onGoingWork) {
            if (endDateWork != null) {
                throw new InvalidWorkEndDateException("End date must be empty for ongoing work");
            }
        } else {
            if (endDateWork == null) {
                throw new InvalidWorkEndDateException("End date is required if work is not ongoing");
            }

            if (startDateWork != null && endDateWork.isBefore(startDateWork)) {
                throw new InvalidWorkEndDateException("End date cannot be before start date");
            }
        }
    }

}
