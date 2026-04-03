package com.prolink.prolink.service;

import com.prolink.prolink.exceptionhandler.EmailAlreadyExistsException;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
     this.userRepository = userRepository;
    }

    public User register(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User(email, password);
        return userRepository.save(user);
    }

}