package com.fullstack.ecom_spring_back.entity;

import com.fullstack.ecom_spring_back.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sells")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sell extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Double price;
    private Double sellPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}