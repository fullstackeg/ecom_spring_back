package com.fullstack.ecom_spring_back.controller;

import com.fullstack.ecom_spring_back.entity.Category;
import com.fullstack.ecom_spring_back.entity.Product;
import com.fullstack.ecom_spring_back.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //POST: Create Category
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    //POST: Update Category
    @PostMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
       Category updatedCategory = categoryService.updateCategory(id, category);
       return ResponseEntity.ok(updatedCategory);
    }

    //GET: All Categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //GET: Products By Category
    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long id) {
        return categoryService.getProductsByCategoryId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
