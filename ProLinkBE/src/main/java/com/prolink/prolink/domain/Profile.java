package com.prolink.prolink.domain;
import com.prolink.prolink.entity.UserEntity;

public class Profile {
    private Long idProfile;
    private String name;
    private String location;
    private String personalDetails;
    private Long userId;


    public Profile(Long idProfile,String name,String location,String personalDetails,Long userId){
        this.idProfile=idProfile;
        this.name=name;
        this.location=location;
        this.personalDetails=personalDetails;
        this.userId=userId;
    }

    public Profile(String name,String location,String personalDetails,Long userId){
        this.name=name;
        this.location=location;
        this.personalDetails=personalDetails;
        this.userId=userId;
    }

    public Long getIdProfile(){return idProfile;}
    public String getName(){return name;}
    public String getLocation(){return location;}
    public String getPersonalDetails(){return personalDetails;}
    public Long getUserId(){return userId;}

    public void setName(String name){this.name=name;}
    public void setLocation(String location){this.location=location;}
    public void setPersonalDetails(String personalDetails){this.personalDetails=personalDetails;}
}
