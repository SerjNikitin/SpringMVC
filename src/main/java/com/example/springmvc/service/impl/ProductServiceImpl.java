package com.example.springmvc.service.impl;

import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProductById(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByCategoryId(Integer categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByTitleAndByMaxAndMinPrice(String title, Integer minPrice, Integer maxPrice) {
        if (Objects.isNull(minPrice)){
            minPrice=0;
        }
        if (Objects.isNull(maxPrice)){
            maxPrice=Integer.MAX_VALUE;
        }
        return productRepository.findProductsByTitleContainingAndPriceBetween(title, minPrice, maxPrice);
    }
}