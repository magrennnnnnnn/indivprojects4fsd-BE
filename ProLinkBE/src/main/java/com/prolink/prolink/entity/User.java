package com.prolink.prolink.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    public User(){
        this.email=email;
        this.password=password;
    }

    public Long Id(){
        return id;
    }

    public String Email(){
        return email;
    }
    public String Password(){
        return password;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPassword(String password){
        this.password=password;
    }
}

