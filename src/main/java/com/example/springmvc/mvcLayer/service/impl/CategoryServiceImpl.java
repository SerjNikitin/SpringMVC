package com.example.springmvc.mvcLayer.service.impl;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.repository.CategoryRepository;
import com.example.springmvc.mvcLayer.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategories() {
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
    public Set<Category> findCategoryById(Set<Integer> id) {
        return categoryRepository.findCategoryByIdIn(id);
    }

    @Override
    public Set<Integer> getCategoryIdList(Set<Category> categories) {
        Set<Integer> ids = new HashSet<>();
        categories.forEach(category -> ids.add(category.getId()));
        return ids;
    }

    @Override
    public Optional<Category> findCategory(Integer id) {
        return categoryRepository.findById(id);
    }


}