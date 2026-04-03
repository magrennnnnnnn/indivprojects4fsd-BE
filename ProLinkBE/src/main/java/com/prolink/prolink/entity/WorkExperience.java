package com.prolink.prolink.entity;

import com.prolink.prolink.enums.WorkLocation;
import com.prolink.prolink.enums.WorkScheduleType;
import com.prolink.prolink.enums.WorkType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "work")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfileWork;
    private String workInstitutionName;
    private LocalDate startDateWork;
    private LocalDate endDateWork;
    private boolean onGoingWork;

    @Column(length = 2000)
    private String workSkills;

    @Enumerated(EnumType.STRING)
    private WorkType work;

    @Enumerated(EnumType.STRING)
    private WorkLocation workLocation;

    @Enumerated(EnumType.STRING)
    private WorkScheduleType workScheduleType;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private ProfileEntity profileEntity;

    public WorkExperience(){}
    public WorkExperience(String workInstitutionName, String workSkills, LocalDate startDateWork, LocalDate endDateWork, ProfileEntity profileEntity, WorkType work, boolean onGoingWork, WorkLocation workLocation, WorkScheduleType workScheduleType){
        this.workInstitutionName=workInstitutionName;
        this.workSkills=workSkills;
        this.startDateWork=startDateWork;
        this.endDateWork=endDateWork;
        this.profileEntity = profileEntity;
        this.work=work;
        this.workLocation=workLocation;
        this.workScheduleType=workScheduleType;
        this.onGoingWork=onGoingWork;
    }

    public Long getIdProfileWork(){return idProfileWork;}

    public String getWorkInstitutionName(){return workInstitutionName;}

    public String getWorkSkills(){return workSkills;}

    public LocalDate getStartDateWork(){return startDateWork;}

    public LocalDate getEndDateWork(){return endDateWork;}

    public ProfileEntity getProfile(){return profileEntity;}

    public WorkType getWork(){return work;}

    public WorkScheduleType getWorkScheduleType(){return workScheduleType;}

    public WorkLocation getWorkLocation(){return workLocation;}

    public boolean isOnGoingWork(){return onGoingWork;}

    public void setIdProfileWork(Long idProfileWork){this.idProfileWork=idProfileWork;}

    public void setWorkInstitutionName(String workInstitutionName){this.workInstitutionName=workInstitutionName;}

    public void setWorkSkills(String workSkills){this.workSkills=workSkills;}

    public void setStartDateWork(LocalDate startDateWork){this.startDateWork=startDateWork;}

    public void setEndDateWork(LocalDate endDateWork){this.endDateWork=endDateWork;}

    public void setWork(WorkType work){this.work=work;}

    public void setProfile(ProfileEntity profileEntity){this.profileEntity = profileEntity;}

    public void setWorkScheduleType(WorkScheduleType workScheduleType){this.workScheduleType=workScheduleType;}

    public void setWorkLocation(WorkLocation workLocation){this.workLocation=workLocation;}

    public void setOnGoingWork(boolean onGoingWork){this.onGoingWork=onGoingWork;}

}
