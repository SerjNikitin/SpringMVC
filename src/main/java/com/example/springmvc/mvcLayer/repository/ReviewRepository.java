package com.example.springmvc.mvcLayer.repository;

import com.example.springmvc.mvcLayer.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ProductReview, Integer> {

    List<ProductReview>findProductReviewsByProduct_id(Integer productId);

    void deleteProductReviewsByProduct_Id(Integer productId);

    void deleteProductReviewById(Integer id);
}
