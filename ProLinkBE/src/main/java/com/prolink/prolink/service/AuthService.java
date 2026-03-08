package com.prolink.prolink.service;

import com.prolink.prolink.entity.User;
import com.prolink.prolink.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
     this.userRepository=userRepository;
    }

    public User register(String email, String password) {
     User user = new User(email,password);
        return userRepository.save(user);


    }

}