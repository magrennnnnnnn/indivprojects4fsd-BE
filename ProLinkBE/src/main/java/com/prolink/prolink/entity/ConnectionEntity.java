package com.prolink.prolink.entity;

import com.prolink.prolink.enums.ConnectionStatusType;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "connections")
public class ConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConnection;

    @ManyToOne
    @JoinColumn(name = "requester_profile_id", nullable = false)
    private ProfileEntity requesterProfile;

    @ManyToOne
    @JoinColumn(name = "receiver_profile_id", nullable = false)
    private ProfileEntity receiverProfile;

    @Enumerated(EnumType.STRING)
    private ConnectionStatusType status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConnectionEntity(){}
    public ConnectionEntity(Long idConnection,ProfileEntity requesterProfile,ProfileEntity receiverProfile,ConnectionStatusType status,LocalDateTime createdAt,LocalDateTime updatedAt){
        this.idConnection=idConnection;
        this.requesterProfile=requesterProfile;
        this.receiverProfile=receiverProfile;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public Long getIdConnection(){return idConnection;}
    public ProfileEntity getRequesterProfile(){return requesterProfile;}
    public ProfileEntity getReceiverProfile(){return receiverProfile;}
    public ConnectionStatusType getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}

    public void setIdConnection(Long idConnection){this.idConnection=idConnection;}
    public void setRequesterProfile(ProfileEntity requesterProfile){this.requesterProfile=requesterProfile;}
    public void setReceiverProfile(ProfileEntity receiverProfile){this.receiverProfile=receiverProfile;}
    public void setStatus(ConnectionStatusType status){this.status=status;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public void setUpdatedAt(LocalDateTime updatedAt){this.updatedAt=updatedAt;}









}
