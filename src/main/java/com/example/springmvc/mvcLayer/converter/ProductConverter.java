package com.example.springmvc.mvcLayer.converter;

import com.example.springmvc.mvcLayer.domain.productMarket.Product;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.service.CategoryService;

public class ProductConverter {
    private static CategoryService categoryService;
    public static ProductDto productConvertToDtoProduct(Product product) {
       return ProductDto.builder().id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .categories(product.getCategories()).build();
    }

    public static Product dtoProductConvertToProduct(ProductDto productDto, Product product) {
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategories(productDto.getCategories());
        return product;
    }
}