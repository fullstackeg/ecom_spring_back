package com.fullstack.ecom_spring_back.repository;

import com.fullstack.ecom_spring_back.entity.Credit;
import com.fullstack.ecom_spring_back.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByStatus(Status status);
    List<Credit> findByClientId(Long clientId);
}
