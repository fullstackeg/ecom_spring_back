package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.Category;
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
    public Product addProduct(Product product) {
        if(productRepository.existsByNameIgnoreCase(product.getName())) {
            throw new RuntimeException("Product with name " + product.getName() + " already exists");
        }
        return productRepository.save(product);
    }

    //update Product
    public Product updateProduct(Long id, Product updated) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " does not exist"));
        if(!existing.getName().equalsIgnoreCase(updated.getName()) && productRepository.existsByNameIgnoreCase(updated.getName())) {
            throw new RuntimeException("Product with name " + updated.getName() + " already exists");
        }
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setSellPrice(updated.getSellPrice());
        existing.setStock(updated.getStock());
        existing.setCategory(updated.getCategory());
        return productRepository.save(existing);
    }

    //get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
