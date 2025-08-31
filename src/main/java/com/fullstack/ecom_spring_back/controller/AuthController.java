package com.fullstack.ecom_spring_back.controller;

import com.fullstack.ecom_spring_back.dto.request.LoginRequest;
import com.fullstack.ecom_spring_back.dto.request.RegisterRequest;
import com.fullstack.ecom_spring_back.dto.response.LoginResponse;
import com.fullstack.ecom_spring_back.entity.User;
import com.fullstack.ecom_spring_back.service.CustomDetailsService;
import com.fullstack.ecom_spring_back.service.UserService;
import com.fullstack.ecom_spring_back.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomDetailsService customDetailsService;

    //Register
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        //create User entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        //save user
        User saved = userService.register(user);

        //load UserDetails to generate JWT
        UserDetails userDetails = customDetailsService.loadUserByUsername(saved.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        String role = jwtUtil.extractRole(token);

        //return token + role
        return ResponseEntity.ok(new LoginResponse(token, role));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //load user details
        UserDetails userDetails = customDetailsService.loadUserByUsername(request.getUsername());

        //generate JWT token
        String token = jwtUtil.generateToken(userDetails);

        //return token + role
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        return ResponseEntity.ok(new LoginResponse(token, role));
    }
}
