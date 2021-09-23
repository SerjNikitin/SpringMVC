package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findCategory();

    void addCategory(String title);
}
