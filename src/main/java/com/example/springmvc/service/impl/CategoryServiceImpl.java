package com.example.springmvc.service.impl;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.CategoryRepository;
import com.example.springmvc.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCategory(Integer id, String title) {

        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setTitle(title);
            categoryRepository.save(category);
        }
    }

    @Override
    public List<Category> findCategory() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void addCategory(String title) {
        Optional<Category> categoryByTitle = categoryRepository.findCategoryByTitle(title);
        if (categoryByTitle.isEmpty()) {
            categoryRepository.save(new Category(title));
        }
    }

    @Override
    public List<Product> findProductsByCategoryId(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        return category.getProducts();
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

}
