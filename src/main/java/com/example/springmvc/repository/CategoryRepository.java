package com.example.springmvc.repository;

import com.example.springmvc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByTitle(String title);
}
