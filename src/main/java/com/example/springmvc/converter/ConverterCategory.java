package com.example.springmvc.converter;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.dto.CategoryDto;
import com.example.springmvc.service.ProductService;

import java.util.Set;

public class ConverterCategory {
    private static ProductService productService;

    public static CategoryDto convert(Category category){
        return CategoryDto.builder().id(category.getId())
                .title(category.getTitle())
                .productsDto(productService.findProductsDtoByCategoryId(category.getId()))
                .build();

    }
}
