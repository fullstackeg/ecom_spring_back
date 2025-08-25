package com.fullstack.ecom_spring_back.repository;

import com.fullstack.ecom_spring_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
