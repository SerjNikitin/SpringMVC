package com.example.springmvc.mvcLayer.controller;

//import com.example.springmvc.mvcLayer.service.ProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class ProductControllerTest extends SpringMvcApplicationTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductService productService;
//
//    @BeforeEach
//    void setUp() {
//        Category category = new Category(1,"fruits");
//        Product product = new Product(1, "orange", 22);
//        Mockito.when(productService.findProducts())
//                .thenReturn(Collections.singletonList(product));
//
//        Mockito.when(productService.findProductById(1))
//                .thenReturn(Optional.of(product));
//    }
//
////    @Test
////    void getAllProduct() throws Exception {
////        Category category = new Category("fruits");
////        mockMvc.perform(get("/product"))
////                .andExpect(status().isOk())
////                .andExpect(view().name("getAllProduct"))
////                .andExpect(model().attribute("products",
////                        Collections.singletonList(new Product(1, "orange", 22, category))));
////    }
//
////    .andExpect(model().attribute("category", category))
//
//    @Test
//    void addFormToAddProduct() throws Exception {
//        Product product = new Product();
//        mockMvc.perform(get("/product/add-product"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("addProduct"))
//                .andExpect(model().attribute("product", product));
//    }
//
//    @Test
//    void addProductsWithoutId() throws Exception {
//        mockMvc.perform(post("/product/add-product")
//                        .param("name", "title")
//                        .param("price", "20")
//                        .param("category", "fruits"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("error"));
//    }
//
//    @Test
//    void addProductsWithoutName() throws Exception {
//        mockMvc.perform(post("/product/add-product")
//                        .param("id", "1")
//                        .param("title", "")
//                        .param("price", "20")
//                        .param("category", "fruits"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/product"))
//                .andExpect(flash().attribute("error", "Title не может быть пустым"));
//    }
//
////    @Test
////    void filterById() throws Exception {
////        Category category = new Category("fruits");
////        Product product = new Product(1, "orange", 22,category);
////        mockMvc.perform(get("/product/findById?id=1"))
////                .andExpect(status().isOk())
////                .andExpect(model().attribute("product", product))
////                .andExpect(model().attribute("category",category))
////                .andExpect(view().name("getAllProduct"));
////    }
//
////    @Test
////    void correctAddProducts() throws Exception {
////        Category category = new Category("fruits");
////        Product product = new Product(1, "orange", 22);
////        mockMvc.perform(post("/product/add-product")
////                        .param("id", "1")
////                        .param("title", "orange")
////                        .param("price", "22"))
////                .andExpect(status().isOk());
//////                .andExpect(status().is3xxRedirection())
////                .andExpect(redirectedUrl("/product"));
////        Mockito.verify(productService, Mockito.times(1)).saveProduct(product);
//    }
//}