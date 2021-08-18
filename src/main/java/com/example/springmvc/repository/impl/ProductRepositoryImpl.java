package com.example.springmvc.repository.impl;

import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "meat", 106.1));
        products.add(new Product(2, "bread", 10.1));
        products.add(new Product(3, "orange", 6.1));
        products.add(new Product(4, "milk", 2.1));
        products.add(new Product(5, "vegetable", 189.1));
    }

    @Override
    public List<Product> findProducts() {
        return products;
    }

    @Override
    public Optional<Product> findProductId(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addProduct(Product product) {
        int size = products.size()-1;
        int id = products.get(size).getId();
        id++;
        product.setId(id);
        products.add(product);
    }
}