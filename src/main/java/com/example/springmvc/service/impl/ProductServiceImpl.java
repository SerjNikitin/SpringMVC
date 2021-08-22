package com.example.springmvc.service.impl;

import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findProducts() {
        return productRepository.findProducts();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.saveProduct(product);
    }

    @Override
    public void updateProductById(Integer id, String title, Integer price) {
        productRepository.updateProductById(id,title,price);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);
    }
}

