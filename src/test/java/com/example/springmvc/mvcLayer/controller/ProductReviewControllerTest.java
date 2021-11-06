package com.example.springmvc.mvcLayer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.ProductReview;
import com.example.springmvc.mvcLayer.repository.CategoryRepository;
import com.example.springmvc.mvcLayer.repository.ProductRepository;
import com.example.springmvc.mvcLayer.repository.ReviewRepository;
import com.example.springmvc.mvcLayer.repository.UserRepository;
import com.example.springmvc.mvcLayer.service.FileService;
import com.example.springmvc.mvcLayer.service.ProductService;
import com.example.springmvc.mvcLayer.service.ReviewService;
import com.example.springmvc.mvcLayer.service.RoleService;
import com.example.springmvc.mvcLayer.service.impl.CategoryServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.ProductServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.ReviewServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.security.UserServiceImpl;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {ProductReviewController.class})
@ExtendWith(SpringExtension.class)
class ProductReviewControllerTest {
    @Autowired
    private ProductReviewController productReviewController;

    @MockBean
    private ProductService productService;

    @MockBean
    private ReviewService reviewService;

    @Test
    void testCreateViewForReviewProduct() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById((Integer) any())).thenReturn(Optional.<Product>of(product));
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService, fileService,
                new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null)));

        ReviewRepository reviewRepository1 = mock(ReviewRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        RoleService roleService = mock(RoleService.class);
        ProductReviewController productReviewController = new ProductReviewController(
                new ReviewServiceImpl(reviewRepository1,
                        new UserServiceImpl(userRepository, roleService, new Argon2PasswordEncoder())),
                productService);
        ConcurrentModel concurrentModel = new ConcurrentModel();
        assertEquals("/review/form", productReviewController.createViewForReviewProduct(concurrentModel, 1));
        verify(productRepository).findById((Integer) any());
        assertSame(product, ((ProductReview) concurrentModel.get("productReview")).getProduct());
    }

    @Test
    void testDeleteReview() throws Exception {
        doNothing().when(this.reviewService).deleteReview((Integer) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/review/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("review/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("review/list"));
    }

    @Test
    void testGetReviewByProduct() throws Exception {
        when(this.reviewService.findReviewByProductId((Integer) any())).thenReturn(new ArrayList<ProductReview>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/review");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("reviewList"))
                .andExpect(MockMvcResultMatchers.view().name("review/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("review/list"));
    }
}

