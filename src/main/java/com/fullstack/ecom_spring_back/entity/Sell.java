package com.fullstack.ecom_spring_back.entity;

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
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sellDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "sell_products",
            joinColumns = @JoinColumn(name = "sell_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}