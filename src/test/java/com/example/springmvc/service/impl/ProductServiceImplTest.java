package com.example.springmvc.service.impl;

import com.example.springmvc.SpringMvcApplicationTests;
import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import com.example.springmvc.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


class ProductServiceImplTest extends SpringMvcApplicationTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product = new Product(1, "orange", 22);
        when(productRepository.findProducts())
                .thenReturn(Collections.singletonList(product));

        when(productRepository.findProductById(1))
                .thenReturn(Optional.of(product));

        when(productRepository.findProductById(6))
                .thenReturn(java.util.Optional.empty());
    }

    @Test
    void findProducts() {
        Product product = new Product(1, "orange", 22);
        Assertions.assertEquals(Collections.singletonList(product), productService.findProducts());
    }

    @Test
    void findProductId() {
        Product product = new Product(1, "orange", 22);
        Assertions.assertTrue(productService.findProductById(1).isPresent());
        Assertions.assertEquals(product, productService.findProductById(1).get());
    }

    @Test
    void add() {
        Product product = new Product(1, "orange", 22);
        when(productRepository.findProducts()).thenReturn(Collections.singletonList(product));
        List<Product> products = productService.findProducts();
        Assertions.assertNotNull(products);
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(product, products.get(0));

    }
}