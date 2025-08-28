package com.fullstack.ecom_spring_back.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthEntryPoint customAuthEntryPoint) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() //login / register are public
                    .requestMatchers("/api/products/**").hasRole("ADMIN") //only ADMIN
                    .requestMatchers("/api/sells/**").hasAnyRole("ADMIN", "USER") //both can access
                    .anyRequest().authenticated()
                 )
            .httpBasic(Customizer.withDefaults()) //basic auth (switch to JWT Later)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthEntryPoint)); //handle 401
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
