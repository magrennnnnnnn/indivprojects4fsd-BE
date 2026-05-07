package com.prolink.prolink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProfileRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Personal details are required")
    @Size(min = 10, max = 2000)
    private String personalDetails;

    public CreateProfileRequest() {}

    public String getName() {return name;}

    public String getLocation() {return location;}

    public String getPersonalDetails() {return personalDetails;}

    public void setName(String name) {this.name = name;}

    public void setLocation(String location) {this.location = location;}

    public void setPersonalDetails(String personalDetails) {this.personalDetails = personalDetails;}
}