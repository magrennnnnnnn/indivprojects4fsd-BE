package com.prolink.prolink.dto;

import com.prolink.prolink.domain.Connection;
import com.prolink.prolink.enums.ConnectionStatusType;

import java.time.LocalDateTime;

public class ConnectionResponse {
    private Long idConnection;
    private Long requesterProfileId;
    private Long receiverProfileId;
    private ConnectionStatusType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConnectionResponse(){}

    public ConnectionResponse(Long idConnection,Long requesterProfileId,Long receiverProfileId,ConnectionStatusType status,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConnection=idConnection;
        this.requesterProfileId=requesterProfileId;
        this.receiverProfileId=receiverProfileId;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public static ConnectionResponse fromDomain(Connection connection) {
        return new ConnectionResponse(
                connection.getIdConnection(),
                connection.getRequesterProfileId(),
                connection.getReceiverProfileId(),
                connection.getStatus(),
                connection.getCreatedAt(),
                connection.getUpdatedAt()
        );
    }

    public Long getIdConnection(){return idConnection;}

    public Long getRequesterProfileId(){return requesterProfileId;}

    public Long getReceiverProfileId(){return receiverProfileId;}

    public ConnectionStatusType getStatus(){return status;}

    public LocalDateTime getCreatedAt(){return createdAt;}

    public LocalDateTime getUpdatedAt(){return updatedAt;}


}
