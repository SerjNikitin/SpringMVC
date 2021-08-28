package com.example.springmvc.repository;

import com.example.springmvc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    void deleteById(Integer id);

    List<Product> findProductsByCategoryId(Integer categoryId);

    List<Product> findProductsByTitleContainingAndPriceBetween(String title, Integer minPrice, Integer maxPrice);
}
