package com.prolink.prolink.domain;

import com.prolink.prolink.enums.ConnectionStatusType;

import java.time.LocalDateTime;

public class Connection {
    private Long idConnection;
    private Long requesterProfileId;
    private String requesterProfileName;
    private String requesterProfileLocation;
    private String chatii;

    private Long receiverProfileId;
    private String receiverProfileName;
    private String receiverProfileLocation;

    private ConnectionStatusType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Connection(){}

    public Connection(Long idConnection,Long requesterProfileId,String requesterProfileName,String requesterProfileLocation,Long receiverProfileId,String receiverProfileName,String receiverProfileLocation,ConnectionStatusType status,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConnection=idConnection;
        this.requesterProfileId=requesterProfileId;
        this.requesterProfileName=requesterProfileName;
        this.requesterProfileLocation=requesterProfileLocation;
        this.receiverProfileId=receiverProfileId;
        this.receiverProfileName=receiverProfileName;
        this.requesterProfileLocation=receiverProfileLocation;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public Long getIdConnection(){return idConnection;}
    public Long getRequesterProfileId(){return requesterProfileId;}
    public String getRequesterProfileName(){return requesterProfileName;}
    public String getRequesterProfileLocation(){return requesterProfileLocation;}
    public Long getReceiverProfileId(){return receiverProfileId;}
    public String getReceiverProfileName(){return receiverProfileName;}
    public String getReceiverProfileLocation(){return receiverProfileLocation;}
    public ConnectionStatusType getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}

    public void setRequesterProfileId(Long requesterProfileId){this.requesterProfileId=requesterProfileId;}
    public void setRequesterProfileName(String requesterProfileName){this.receiverProfileName=requesterProfileName;}
    public void setRequesterProfileLocation(String requesterProfileLocation){this.requesterProfileLocation=requesterProfileLocation;}
    public void setReceiverProfileId(Long receiverProfileId){this.receiverProfileId=receiverProfileId;}
    public void setReceiverProfileName(String receiverProfileName){this.receiverProfileName=receiverProfileName;}
    public void setReceiverProfileLocation(String receiverProfileLocation){this.receiverProfileLocation=receiverProfileLocation;}
    public void setStatus(ConnectionStatusType status){this.status=status;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public void setUpdatedAt(LocalDateTime updatedAt){this.updatedAt=updatedAt;}

}
