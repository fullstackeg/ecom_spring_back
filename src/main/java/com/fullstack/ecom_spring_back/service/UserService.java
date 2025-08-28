package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.User;
import com.fullstack.ecom_spring_back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        //Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //default role if none provided
        if(user.getRole() == null) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
