package com.example.springmvc.service;

import com.example.springmvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findProducts();

    Optional<Product> findProductById(Integer id);

    void saveProduct(Product product);

    void updateProductById(Integer id, String title, Integer price);

    void deleteProductById(Integer id);
}