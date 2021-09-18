package com.example.springmvc.mvcLayer.service.impl;

import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import com.example.springmvc.mvcLayer.repository.CategoryRepository;
import com.example.springmvc.mvcLayer.service.CategoryService;
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
}