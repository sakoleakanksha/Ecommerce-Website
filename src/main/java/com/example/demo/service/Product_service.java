package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class Product_service {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        Category category = categoryRepo.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category Not Found with id: " + categoryId));
        return productRepo.findByCategory(category);
    }

    public List<Product> searchProductsByName(String keyword) {
        return productRepo.findByNameContaining(keyword);
    }

    public Product updateProduct(Integer id, Product product) {
        Product existing = getProductById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setWeight(product.getWeight());
        existing.setCategory(product.getCategory());
        existing.setImage(product.getImage());
        return productRepo.save(existing);
    }

    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }
}
