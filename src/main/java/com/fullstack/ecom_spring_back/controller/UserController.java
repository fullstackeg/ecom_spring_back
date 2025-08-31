package com.fullstack.ecom_spring_back.controller;

import com.fullstack.ecom_spring_back.entity.User;
import com.fullstack.ecom_spring_back.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return ResponseEntity.ok(users);
    }
}
