package com.prolink.prolink.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    public UserEntity(){}
    public UserEntity(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Long getId(){
        return id;
    }

    public String getEmail() {return email;}

    public String getPassword() {
        return password;
    }

    public void setEmail(String email){this.email=email;}

    public void setPassword(String password){
        this.password=password;
    }

    public void setId(Long id){this.id=id;}
}

