package com.prolink.prolink.entity;

import com.prolink.prolink.enums.Roles;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    public UserEntity(){}
    public UserEntity(String email, String password,Roles roles){
        this.email=email;
        this.password=password;
        this.roles=roles;
    }

    public Long getId(){
        return id;
    }

    public String getEmail() {return email;}

    public String getPassword() {
        return password;
    }

    public Roles getRoles(){return roles;}

    public void setEmail(String email){this.email=email;}

    public void setPassword(String password){
        this.password=password;
    }

    public void setId(Long id){this.id=id;}

    public void setRoles(Roles roles){this.roles=roles;}
}

