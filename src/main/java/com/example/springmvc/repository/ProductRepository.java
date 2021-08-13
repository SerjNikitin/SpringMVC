package com.example.springmvc.repository;

import com.example.springmvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findProducts();

    Optional<Product> findProductId(int id);

    void addProduct(Product product);
}
