package com.prolink.prolink.dto;

public class CreateProfileRequest {
    private Long userId;
    private String name;
    private String location;
    private String personalDetails;
    public CreateProfileRequest() {}

    public Long getUserId() {return userId;}

    public String getName() {return name;}

    public String getLocation() {return location;}

    public String getPersonalDetails() {return personalDetails;}

    public void setUserId(Long userId) {this.userId = userId;}

    public void setName(String name) {this.name = name;}

    public void setLocation(String location) {this.location = location;}

    public void setPersonalDetails(String personalDetails) {this.personalDetails = personalDetails;}
}