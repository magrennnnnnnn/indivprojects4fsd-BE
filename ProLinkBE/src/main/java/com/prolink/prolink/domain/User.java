package com.prolink.prolink.domain;

import com.prolink.prolink.exceptionhandler.InvalidEmailException;
import com.prolink.prolink.exceptionhandler.InvalidPasswordException;
import com.prolink.prolink.exceptionhandler.PasswordIsIncorrectException;

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

    public void validateForRegister() {
        validateEmail();
        validatePassword();
    }


    private void validateEmail() {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new InvalidEmailException("Email format is invalid");
        }
    }

    private void validatePassword() {
        if (password == null || password.isBlank()) {
            throw new InvalidPasswordException("Password cannot be empty");
        }

        if (password.length() < 6) {
            throw new InvalidPasswordException("Password must be at least 6 characters long");
        }
    }

    public void validateLoginPassword(String rawPassword) {
        if (rawPassword == null || !this.password.equals(rawPassword)) {
            throw new PasswordIsIncorrectException("Invalid email or password");
        }
    }
}
