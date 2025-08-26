package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.Product;
import com.fullstack.ecom_spring_back.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Add Product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //update Product
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setSellPrice(updatedProduct.getSellPrice());
            existing.setStock(updatedProduct.getStock());
            existing.setCategory(updatedProduct.getCategory());
            return productRepository.save(existing);
        });
    }

    //get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
