package com.fullstack.ecom_spring_back.entity;

import com.fullstack.ecom_spring_back.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private Long productId;
    private Double price;
    private Double sellPrice;
    @Enumerated(EnumType.STRING)
    private Status status;
}
