package com.prolink.prolink.dto;

import com.prolink.prolink.enums.Roles;

public class UserResponse {
    private Long id;
    private String email;
    private Roles roles;

    public UserResponse(Long id,String email,Roles roles){
        this.id=id;
        this.email=email;
        this.roles=roles;
    }

    public Long getId(){return id;}
    public String getEmail(){return email;}
    public Roles getRoles(){return roles;}
}
