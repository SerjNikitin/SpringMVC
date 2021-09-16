package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import com.example.springmvc.mvcLayer.domain.productMarket.Product;
import com.example.springmvc.mvcLayer.domain.dto.CategoryDto;

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

//    Page<Product> findAllBySearchConditional(ProductSearchCondition searchCondition);
}
