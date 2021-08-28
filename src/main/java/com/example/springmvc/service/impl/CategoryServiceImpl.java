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

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategory() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public void addCategory(String title) {
        Optional<Category> categoryByTitle = categoryRepository.findCategoryByTitle(title);
        if (categoryByTitle.isEmpty()){
            categoryRepository.save(new Category(title));
        }
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
