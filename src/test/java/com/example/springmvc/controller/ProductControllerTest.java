package com.example.springmvc.controller;

import com.example.springmvc.SpringMvcApplicationTests;
import com.example.springmvc.domain.Product;
import com.example.springmvc.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest extends SpringMvcApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        Product product = new Product(1, "orange", 22);
        Mockito.when(productService.findProducts())
                .thenReturn(Collections.singletonList(product));
    }

    @Test
    void getAllProduct() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(model().attribute("products",
                        Collections.singletonList(new Product(1, "orange", 22))));

    }

    @Test
    void addFormToAddProduct() throws Exception {
        Product product = new Product();
        mockMvc.perform(get("/product/add-product"))
                .andExpect(status().isOk())
                .andExpect(view().name("addProduct"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void addProduct() throws Exception {
        mockMvc.perform(post("/product/add-product"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/product"));
    }

    @Test
    void addFormGetProductById() throws Exception {
        Product product = new Product();
        mockMvc.perform(get("/product/id"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("product", product))
                .andExpect(view().name("productId"));
    }
}