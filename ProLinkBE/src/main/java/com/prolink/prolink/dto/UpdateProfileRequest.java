package com.prolink.prolink.dto;

public class UpdateProfileRequest {
    private String name;
    private String location;
    private String personalDetails;

    public UpdateProfileRequest(){}

    public String getName(){return name;}
    public String getLocation(){return location;}
    public String getPersonalDetails(){return  personalDetails;}

    public void setName(String name){this.name=name;}
    public void setLocation(String location){this.location=location;}
    public void setPersonalDetails(String personalDetails){this.personalDetails=personalDetails;}
}
