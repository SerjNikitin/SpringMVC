package com.example.springmvc.service;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findCategory();

    void addCategory(String title);

    List<Product>findCategoryById(Integer id);

}
