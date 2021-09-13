package com.example.springmvc.repository;

import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    void deleteById(Integer id);

    List<Product> findProductsByTitleContainingIgnoreCaseAndPriceBetween(String title, Integer minPrice, Integer maxPrice);

    Set<Product> findByCategories_Id(Integer categoryId);

    Page<Product> findAll(Pageable pageable);
}
