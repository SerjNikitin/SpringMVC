package com.example.springmvc.repository;

import com.example.springmvc.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findCategory();

    void createCategory(Category category);

    Optional<Category> findCategoryByTitle(String title);

    Optional<Category> findCategoryById(Integer id);
}
