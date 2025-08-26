package com.fullstack.ecom_spring_back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = { "name", "category_id"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Double sellPrice;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
