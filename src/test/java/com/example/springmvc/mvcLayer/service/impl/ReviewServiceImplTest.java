package com.example.springmvc.mvcLayer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.ProductReview;
import com.example.springmvc.mvcLayer.domain.security.Role;
import com.example.springmvc.mvcLayer.domain.security.User;
import com.example.springmvc.mvcLayer.repository.ReviewRepository;
import com.example.springmvc.mvcLayer.service.UserService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReviewServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReviewServiceImplTest {
    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @MockBean
    private UserService userService;

    @Test
    void testFindReviewByProductId() {
        ArrayList<ProductReview> productReviewList = new ArrayList<ProductReview>();
        when(this.reviewRepository.findProductReviewsByProduct_id((Integer) any())).thenReturn(productReviewList);
        List<ProductReview> actualFindReviewByProductIdResult = this.reviewServiceImpl.findReviewByProductId(1);
        assertSame(productReviewList, actualFindReviewByProductIdResult);
        assertTrue(actualFindReviewByProductIdResult.isEmpty());
        verify(this.reviewRepository).findProductReviewsByProduct_id((Integer) any());
    }

    @Test
    void testFindReviewByProductId2() {
        ArrayList<ProductReview> productReviewList = new ArrayList<ProductReview>();
        when(this.reviewRepository.findProductReviewsByProduct_id((Integer) any())).thenReturn(productReviewList);
        List<ProductReview> actualFindReviewByProductIdResult = this.reviewServiceImpl.findReviewByProductId(1);
        assertSame(productReviewList, actualFindReviewByProductIdResult);
        assertTrue(actualFindReviewByProductIdResult.isEmpty());
        verify(this.reviewRepository).findProductReviewsByProduct_id((Integer) any());
    }

    @Test
    void testSaveReview() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        when(this.userService.findByUsername((String) any())).thenReturn(user);

        User user1 = new User();
        user1.setPassword("iloveyou");
        user1.setEmail("jane.doe@example.org");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setRoles(new ArrayList<Role>());

        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");

        ProductReview productReview = new ProductReview();
        productReview.setRating(1);
        productReview.setReview("Review");
        productReview.setId(1);
        productReview.setUser(user1);
        productReview.setProduct(product);
        productReview.setDateCreate(LocalDate.ofEpochDay(1L));
        when(this.reviewRepository.save((ProductReview) any())).thenReturn(productReview);

        User user2 = new User();
        user2.setPassword("iloveyou");
        user2.setEmail("jane.doe@example.org");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setRoles(new ArrayList<Role>());

        Product product1 = new Product();
        product1.setCountProduct(3);
        product1.setId(1);
        product1.setTitle("Dr");
        product1.setCategories(new HashSet<Category>());
        product1.setPrice(1);
        product1.setImage("Image");

        ProductReview productReview1 = new ProductReview();
        productReview1.setRating(1);
        productReview1.setReview("Review");
        productReview1.setId(1);
        productReview1.setUser(user2);
        productReview1.setProduct(product1);
        productReview1.setDateCreate(LocalDate.ofEpochDay(1L));
        this.reviewServiceImpl.saveReview(productReview1, "Name");
        verify(this.userService).findByUsername((String) any());
        verify(this.reviewRepository).save((ProductReview) any());
        assertEquals(user2, productReview1.getUser());
    }

    @Test
    void testSaveReview2() {
        User user = new User();
        user.setPassword("iloveyou");
        user.setEmail("jane.doe@example.org");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>());
        when(this.userService.findByUsername((String) any())).thenReturn(user);

        User user1 = new User();
        user1.setPassword("iloveyou");
        user1.setEmail("jane.doe@example.org");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setRoles(new ArrayList<Role>());

        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");

        ProductReview productReview = new ProductReview();
        productReview.setRating(1);
        productReview.setReview("Review");
        productReview.setId(1);
        productReview.setUser(user1);
        productReview.setProduct(product);
        productReview.setDateCreate(LocalDate.ofEpochDay(1L));
        when(this.reviewRepository.save((ProductReview) any())).thenReturn(productReview);

        User user2 = new User();
        user2.setPassword("iloveyou");
        user2.setEmail("jane.doe@example.org");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setRoles(new ArrayList<Role>());

        Product product1 = new Product();
        product1.setCountProduct(3);
        product1.setId(1);
        product1.setTitle("Dr");
        product1.setCategories(new HashSet<Category>());
        product1.setPrice(1);
        product1.setImage("Image");

        ProductReview productReview1 = new ProductReview();
        productReview1.setRating(1);
        productReview1.setReview("Review");
        productReview1.setId(1);
        productReview1.setUser(user2);
        productReview1.setProduct(product1);
        productReview1.setDateCreate(LocalDate.ofEpochDay(1L));
        this.reviewServiceImpl.saveReview(productReview1, "Name");
        verify(this.userService).findByUsername((String) any());
        verify(this.reviewRepository).save((ProductReview) any());
        assertEquals(user2, productReview1.getUser());
    }

    @Test
    void testDeleteAllReviewByProductId() {
        doNothing().when(this.reviewRepository).deleteProductReviewsByProduct_Id((Integer) any());
        this.reviewServiceImpl.deleteAllReviewByProductId(1);
        verify(this.reviewRepository).deleteProductReviewsByProduct_Id((Integer) any());
    }

    @Test
    void testDeleteAllReviewByProductId2() {
        doNothing().when(this.reviewRepository).deleteProductReviewsByProduct_Id((Integer) any());
        this.reviewServiceImpl.deleteAllReviewByProductId(1);
        verify(this.reviewRepository).deleteProductReviewsByProduct_Id((Integer) any());
    }

    @Test
    void testDeleteReview() {
        doNothing().when(this.reviewRepository).deleteProductReviewById((Integer) any());
        this.reviewServiceImpl.deleteReview(123);
        verify(this.reviewRepository).deleteProductReviewById((Integer) any());
    }

    @Test
    void testDeleteReview2() {
        doNothing().when(this.reviewRepository).deleteProductReviewById((Integer) any());
        this.reviewServiceImpl.deleteReview(123);
        verify(this.reviewRepository).deleteProductReviewById((Integer) any());
    }
}

