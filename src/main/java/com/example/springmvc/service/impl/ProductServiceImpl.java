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

//    public ProductServiceImpl(@Qualifier("productRepositoryImpl") ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findProducts();
    }

    @Override
    public Optional<Product> findProductId(int id) {
        return productRepository.findProductId(id);
    }

    @Override
    public void add(Product product) {
        productRepository.addProduct(product);
    }
}

