package com.fullstack.ecom_spring_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcomSpringBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomSpringBackApplication.class, args);
    }

}
