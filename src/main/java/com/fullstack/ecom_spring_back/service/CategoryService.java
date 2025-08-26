package com.fullstack.ecom_spring_back.service;

import com.fullstack.ecom_spring_back.entity.Category;
import com.fullstack.ecom_spring_back.entity.Product;
import com.fullstack.ecom_spring_back.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Add Category
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    //Update Category
    public Optional<Category> updateCategory(Long id, Category category) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(category.getName());
            return categoryRepository.save(existing);
        });
    }

    //Get All Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //Get Products by Category
    public Optional<List<Product>> getProductsByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(Category::getProducts);
    }
}
