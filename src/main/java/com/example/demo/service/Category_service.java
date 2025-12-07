package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class Category_service {

    @Autowired
    private CategoryRepository categoryRepo;

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category updateCategory(int id, Category category) {
        Category existing = getCategoryById(id);
        existing.setCategory(category.getCategory());
        return categoryRepo.save(existing);
    }

    public void deleteCategory(int id) {
        categoryRepo.deleteById(id);
    }
}
