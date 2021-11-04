package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.ProductReview;

import java.util.List;

public interface ReviewService {
    List<ProductReview> findAllReview();

    List<ProductReview> findReviewByProductId(Integer id);

    void saveReview(ProductReview productReview, String name);

    void deleteAllReviewByProductId(Integer id);

    void deleteReview(Integer reviewId);
}
