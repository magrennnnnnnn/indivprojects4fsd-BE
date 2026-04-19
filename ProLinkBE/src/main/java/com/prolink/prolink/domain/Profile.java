package com.prolink.prolink.domain;
import com.prolink.prolink.entity.UserEntity;
import com.prolink.prolink.exceptionhandler.InvalidProfileLocationException;
import com.prolink.prolink.exceptionhandler.InvalidProfileNameException;
import com.prolink.prolink.exceptionhandler.InvalidProfilePersonalDetailsException;

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

    public void validateProfileForCreate(){
        validateName();
        validateLocation();
        validatePersonalDetails();
    }

    public void validateProfileForUpdate(){
        validateName();
        validateLocation();
        validatePersonalDetails();
    }

    public void validateName(){
        if(name == null || name.isBlank()){
            throw new InvalidProfileNameException("Profile name can not be empty");
        }

        if (name.length() < 2) {
            throw new InvalidProfileNameException("Profile name must be at least 2 characters long");
        }
    }

    public void validateLocation(){
        if(location == null || location.isBlank()){
         throw new InvalidProfileLocationException("Profile location can not be empty");
        }
    }

    public void validatePersonalDetails(){
        if(personalDetails == null || personalDetails.isBlank()){
            throw new InvalidProfilePersonalDetailsException("The personal details can not be empty");
        }

        if(personalDetails.length()<10){
            throw new InvalidProfilePersonalDetailsException("This section must be at least 10 characters long");
        }
    }

}
