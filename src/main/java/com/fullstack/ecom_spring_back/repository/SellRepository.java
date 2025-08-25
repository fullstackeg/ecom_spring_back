package com.fullstack.ecom_spring_back.repository;

import com.fullstack.ecom_spring_back.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {
}
