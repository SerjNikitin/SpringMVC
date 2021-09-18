package com.example.springmvc.mvcLayer.repository;

import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByTitle(String title);

    Set<Category> findByProducts_Id(Integer productId);

//    Page<Product> findProductsByIdCategory(Pageable pageable);

}