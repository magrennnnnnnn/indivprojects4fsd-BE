package com.prolink.prolink.dto;

import com.prolink.prolink.domain.Connection;
import com.prolink.prolink.enums.ConnectionStatusType;

import java.time.LocalDateTime;

public class ConnectionResponse {
    private Long idConnection;
    private Long requesterProfileId;
    private String requesterProfileName;
    private String requesterProfileLocation;

    private Long receiverProfileId;
    private String receiverProfileName;
    private String receiverProfileLocation;

    private ConnectionStatusType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConnectionResponse(){/**/}

    @SuppressWarnings("java:S107")
    public ConnectionResponse(Long idConnection,Long requesterProfileId,String requesterProfileName,String requesterProfileLocation,Long receiverProfileId,String receiverProfileName,String receiverProfileLocation,ConnectionStatusType status,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConnection=idConnection;
        this.requesterProfileId=requesterProfileId;
        this.requesterProfileName=requesterProfileName;
        this.requesterProfileLocation=requesterProfileLocation;
        this.receiverProfileId=receiverProfileId;
        this.receiverProfileName=receiverProfileName;
        this.receiverProfileLocation=receiverProfileLocation;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public static ConnectionResponse fromDomain(Connection connection) {
        return new ConnectionResponse(
                connection.getIdConnection(),
                connection.getRequesterProfileId(),
                connection.getRequesterProfileName(),
                connection.getRequesterProfileLocation(),
                connection.getReceiverProfileId(),
                connection.getReceiverProfileName(),
                connection.getReceiverProfileLocation(),
                connection.getStatus(),
                connection.getCreatedAt(),
                connection.getUpdatedAt()
        );
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


}
