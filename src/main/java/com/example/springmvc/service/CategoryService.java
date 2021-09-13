package com.example.springmvc.service;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    void deleteCategory(Integer id);

    void updateCategory(Integer id,String title);

    List<Category> findCategory();

    void addCategory(String title);

    List<Product>findProductsByCategoryId(Integer id);

    Optional<Category> findCategoryById(Integer id);

    Set<CategoryDto> getCategoryDtoByProductId(Integer productId);

    Set<Category> findCategoriesByProductId(Integer id);
}
