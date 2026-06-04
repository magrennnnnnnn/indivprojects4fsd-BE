package com.prolink.prolink.service;

import com.prolink.prolink.enums.Roles;
import com.prolink.prolink.exceptionhandler.EmailAlreadyExistsException;
import com.prolink.prolink.exceptionhandler.PasswordIsIncorrectException;
import com.prolink.prolink.domain.User;
import com.prolink.prolink.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService{
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
     this.userRepository = userRepository;
    }

    public User register(String email, String password,Roles roles ) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Roles selectedRole = roles;

        if (selectedRole == null) {
            selectedRole = Roles.STANDARD_USER;
        }

        if (selectedRole == Roles.ADMIN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot register as admin");
        }

        User user = new User(email, password, selectedRole);
        user.validateForRegister();

        return userRepository.save(user);
    }

    public User login(String email,String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new PasswordIsIncorrectException("Invalid email or password"));

        user.validateLoginPassword(password);
        return user;
    }

}