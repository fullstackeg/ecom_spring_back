package com.fullstack.ecom_spring_back.repository;

import com.fullstack.ecom_spring_back.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByPhone(String phone);
}
