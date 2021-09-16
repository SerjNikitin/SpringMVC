package com.example.springmvc.mvcLayer.converter;

import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import com.example.springmvc.mvcLayer.domain.dto.CategoryDto;
import com.example.springmvc.mvcLayer.service.ProductService;

public class ConverterCategory {
    private static ProductService productService;

    public static CategoryDto convert(Category category){
        return CategoryDto.builder().id(category.getId())
                .title(category.getTitle())
                .productsDto(productService.findProductsDtoByCategoryId(category.getId()))
                .build();

    }
}
