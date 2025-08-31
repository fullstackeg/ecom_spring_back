package com.fullstack.ecom_spring_back.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @Size(min = 5, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;
}
