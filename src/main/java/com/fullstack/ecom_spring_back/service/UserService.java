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
        if(user.getUsername() == null || user.getPassword() == null || user.getEmail() ==  null) {
            throw new RuntimeException("Username, Password and Email are required");
        }

        if(user.getPassword().length() < 6) {
            throw new RuntimeException("Password must be at least 5 characters");
        }
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User Already exists");
        }

        if(user.getEmail() != null && userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User Already exists");
        }
        //Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
