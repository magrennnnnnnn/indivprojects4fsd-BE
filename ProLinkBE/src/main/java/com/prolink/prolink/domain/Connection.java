package com.prolink.prolink.domain;

import com.prolink.prolink.enums.ConnectionStatusType;

import java.time.LocalDateTime;

public class Connection {
    private Long idConnection;
    private Long requesterProfileId;
    private Long receiverProfileId;
    private ConnectionStatusType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Connection(){}

    public Connection(Long idConnection,Long requesterProfileId,Long receiverProfileId,ConnectionStatusType status,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConnection=idConnection;
        this.requesterProfileId=requesterProfileId;
        this.receiverProfileId=receiverProfileId;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public Long getIdConnection(){return idConnection;}
    public Long getRequesterProfileId(){return requesterProfileId;}
    public Long getReceiverProfileId(){return receiverProfileId;}
    public ConnectionStatusType getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}

    public void setRequesterProfileId(Long requesterProfileId){this.requesterProfileId=requesterProfileId;}
    public void setReceiverProfileId(Long receiverProfileId){this.receiverProfileId=receiverProfileId;}
    public void setStatus(ConnectionStatusType status){this.status=status;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public void setUpdatedAt(LocalDateTime updatedAt){this.updatedAt=updatedAt;}

}
