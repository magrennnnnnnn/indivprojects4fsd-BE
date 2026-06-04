package com.prolink.prolink.dto;

import com.prolink.prolink.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email can not be longer than 255 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    private Roles roles;

    public String getEmail(){return email;}

    public String getPassword(){return password;}

    public Roles getRoles(){return roles;}

    public void setEmail(String email){this.email=email;}

    public void setPassword(String password){this.password=password;}

    public void setRoles(Roles roles){this.roles=roles;}

}