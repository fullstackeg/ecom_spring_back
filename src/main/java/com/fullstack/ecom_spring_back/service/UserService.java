package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.User;
import com.fullstack.ecom_spring_back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername().toLowerCase());
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail().toLowerCase());

        if (byUsername.isPresent() || byEmail.isPresent()) {
            throw new RuntimeException("Username or email address already in use");
        }

        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
