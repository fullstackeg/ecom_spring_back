package com.fullstack.ecom_spring_back.repository;

import com.fullstack.ecom_spring_back.entity.Sell;
import com.fullstack.ecom_spring_back.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {
    List<Sell> findByStatus(Status status);
}
