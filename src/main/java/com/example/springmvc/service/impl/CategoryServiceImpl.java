package com.example.springmvc.service.impl;

//import com.example.springmvc.converter.Converter;
//import com.example.springmvc.converter.ConverterCategory;
import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.dto.CategoryDto;
import com.example.springmvc.repository.CategoryRepository;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
//    private final ProductService productService;

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCategory(Integer id, String title) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setTitle(title);
            categoryRepository.save(category);
        }
    }

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

    @Override
    public List<Product> findProductsByCategoryId(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        return category.getProducts();
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Set<CategoryDto> getCategoryDtoByProductId(Integer productId) {
        Set<Category> categoriesByProductId = categoryRepository.findByProducts_Id(productId);
        Set<CategoryDto>categoryDtoSet=new HashSet<>();
        for (Category category : categoriesByProductId) {
            CategoryDto convert = convert(category);
            categoryDtoSet.add(convert);
        }
        return categoryDtoSet;
    }

    @Override
    public Set<Category> findCategoriesByProductId(Integer id) {
        return categoryRepository.findByProducts_Id(id);
    }

    public CategoryDto convert(Category category){
        return CategoryDto.builder().id(category.getId())
                .title(category.getTitle())
//                .productsDto(productService.findProductsDtoByCategoryId(category.getId()))
                .build();

    }
}