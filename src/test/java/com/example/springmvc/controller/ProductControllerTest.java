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


        Mockito.when(productService.findProductId(1)).thenReturn(Optional.of(product));

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
    void addProductsWithoutId() throws Exception {
        mockMvc.perform(post("/product/add-product")
                        .param("name", "title")
                        .param("price", "20"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    void addProductsWithoutName() throws Exception {
        mockMvc.perform(post("/product/add-product")
                        .param("id", "1")
                        .param("title", "")
                        .param("price", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/add-product"))
                .andExpect(flash().attribute("error", "Title не может быть пустым"));
    }

    @Test
    void filterById() throws Exception {
        Product product = new Product(1, "orange", 22);
        mockMvc.perform(get("/product/findById?id=1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", Collections.singletonList(product)))
                .andExpect(view().name("getProduct"));
    }

    //TODO внизу которые не заработали

    @Test
    void correctAddProducts() throws Exception {
        Product product = new Product(1, "title", 20);

        mockMvc.perform(post("/product/add-product")
                        .param("id", "1")
                        .param("name", "orange")
                        .param("price", "22"))
                        .andExpect(status().isOk())
                .andExpect(view().name("product"));


//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/product"));

//                .andExpect(status().isOk())
//                .andExpect(view().name("product"));

//        Mockito.verify(productService, Mockito.times(1)).add(product);
    }


    @Test
    void addProduct() throws Exception {
        mockMvc.perform(post("/product/add-product"))
//                .andExpect(status().isOk())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product"));
//                .andExpect(view().name("redirect:/product"));
    }
}