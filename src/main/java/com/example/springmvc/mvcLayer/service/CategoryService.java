package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import com.example.springmvc.mvcLayer.domain.productMarket.Product;
import com.example.springmvc.mvcLayer.domain.dto.CategoryDto;
import com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    List<Category> findCategory();

    void addCategory(String title);
}
