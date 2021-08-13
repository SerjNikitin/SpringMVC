package com.example.springmvc.service;

import com.example.springmvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findProducts();

    Optional<Product> findProductId(int id);

    void add(Product product);
}