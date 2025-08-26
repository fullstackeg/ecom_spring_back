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
        //check if name already exists
        if(categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new RuntimeException("Category with name '" + category.getName() + "' already exists");
        }
        return categoryRepository.save(category);
    }

    //Update Category
    public Category updateCategory(Long id, Category updated) {
        Category existing = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No Category with id '" + id + "' Found"));
        //check if new name already exists (and not the same category)
        if(!existing.getName().equalsIgnoreCase(updated.getName()) && categoryRepository.existsByNameIgnoreCase(updated.getName())) {
            throw new RuntimeException("Category with name '" + updated.getName() + "' already exists");
        }
        existing.setName(updated.getName());
        return categoryRepository.save(existing);
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
