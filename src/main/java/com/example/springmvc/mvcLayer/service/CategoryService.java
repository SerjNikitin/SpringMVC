package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<Category> findCategory();

    void addCategory(String title);

    Set<Category> findCategoryById(Set<Integer> id);

    Set<Integer> getCategoryIdList(Set<Category> categories);

}
