package com.example.springmvc.converter;

import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.dto.ProductDto;

public class Converter {
    public static ProductDto DtoProductConvertInProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategories(product.getCategories());
        return productDto;
    }

    public static Product getProduct(ProductDto productDto, Product product) {
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategories(productDto.getCategories());
        return product;
    }

}
