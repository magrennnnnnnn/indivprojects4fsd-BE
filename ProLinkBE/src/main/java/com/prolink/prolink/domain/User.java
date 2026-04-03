package com.prolink.prolink.domain;

public class User {
    private  Long id;
    private String email;
    private String password;


    public User(Long id, String email, String password){
        this.id=id;
        this.email=email;
        this.password=password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {return id;}

    public String getEmail(){return email;}

    public String getPassword(){return password;}


    public void setEmail(String email){this.email=email;}

    public void setPassword(String password){this.password=password;}

    public boolean isValidEmail() {
        return email != null && email.contains("@");
    }
}
