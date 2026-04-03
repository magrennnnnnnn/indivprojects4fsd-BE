package com.prolink.prolink.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfile;
    private String name;
    private String location;

    @Column(length = 2000)
    private String personalDetails;
    @OneToOne
    @JoinColumn(name = "userid", nullable = false, unique = true)
    private UserEntity userEntity;


    public Profile() {
    }
    public Profile(String name, String location, String personalDetails, UserEntity userEntity) {
        this.name = name;
        this.location = location;
        this.personalDetails = personalDetails;
        this.userEntity = userEntity;
    }
    public Long getId(){return idProfile;}

    public String getName(){return name;}

    public String getLocation(){return location;}

    public String getPersonalDetails(){return personalDetails;}

    public UserEntity getUser(){return userEntity;}

    public void setId(Long idProfile) {this.idProfile = idProfile;}

    public void setName(String name){this.name = name;}

    public void setLocation(String location){this.location = location;}

    public void setPersonalDetails(String personalDetails){this.personalDetails = personalDetails;}

    public void setUser(UserEntity userEntity){this.userEntity = userEntity;}

}