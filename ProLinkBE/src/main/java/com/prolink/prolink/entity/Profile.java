package com.prolink.prolink.entity;

import com.prolink.prolink.entity.EducationalExperience;
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
    private User user;


    public Profile() {
    }
    public Profile(String name, String location, String personalDetails, User user) {
        this.name = name;
        this.location = location;
        this.personalDetails = personalDetails;
        this.user = user;
    }
    public Long getId(){return idProfile;}

    public String getName(){return name;}

    public String getLocation(){return location;}

    public String getPersonalDetails(){return personalDetails;}

    public User getUser(){return user;}

    public void setId(Long idProfile) {this.idProfile = idProfile;}

    public void setName(String name){this.name = name;}

    public void setLocation(String location){this.location = location;}

    public void setPersonalDetails(String personalDetails){this.personalDetails = personalDetails;}

    public void setUser(User user){this.user = user;}

}