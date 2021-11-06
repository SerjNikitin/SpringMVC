package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    List<Category> findCategories();

    void addCategory(String title);

    Set<Category> findCategoryById(Set<Integer> id);

    Set<Integer> getCategoryIdList(Set<Category> categories);

    Optional<Category> findCategory(Integer id);

}
