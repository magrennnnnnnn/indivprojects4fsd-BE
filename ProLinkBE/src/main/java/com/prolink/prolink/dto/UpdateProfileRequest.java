package com.prolink.prolink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 150, message = "Location can not be longer than 150 characters")
    private String location;

    @NotBlank(message = "Personal details are required")
    @Size(min = 10, max = 2000, message = "Personal details must be between 10 and 2000 characters")
    private String personalDetails;

    public UpdateProfileRequest(){/**/}

    public String getName(){return name;}
    public String getLocation(){return location;}
    public String getPersonalDetails(){return  personalDetails;}

    public void setName(String name){this.name=name;}
    public void setLocation(String location){this.location=location;}
    public void setPersonalDetails(String personalDetails){this.personalDetails=personalDetails;}
}
