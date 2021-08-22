package com.example.springmvc.repository;

import com.example.springmvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findProducts();

    Optional<Product> findProductById(Integer id);

    void saveProduct(Product product);

    void updateProductById(Integer id, String title, Integer price);

    void deleteProductById(Integer id);

}
