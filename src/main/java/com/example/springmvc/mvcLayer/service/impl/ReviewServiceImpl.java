package com.example.springmvc.mvcLayer.service.impl;

import com.example.springmvc.mvcLayer.domain.ProductReview;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.repository.ReviewRepository;
import com.example.springmvc.mvcLayer.service.ReviewService;
import com.example.springmvc.mvcLayer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    @Override
    public List<ProductReview> findReviewByProductId(Integer id) {
        return reviewRepository.findProductReviewsByProduct_id(id);
    }

    @Override
    @Transactional
    public void saveReview(ProductReview productReview, String name) {
        User user = userService.findByUsername(name);
        productReview.setUser(user);
        productReview.setDateCreate(LocalDate.now());
        reviewRepository.save(productReview);
    }

    @Override
    public void deleteAllReviewByProductId(Integer id) {
        reviewRepository.deleteProductReviewsByProduct_Id(id);
    }

    @Override
    @Transactional
    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteProductReviewById(reviewId);
    }
}
