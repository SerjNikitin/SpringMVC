package com.example.springmvc.mvcLayer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.service.CategoryService;
import com.example.springmvc.mvcLayer.service.ProductService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testHandleError() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        assertEquals("error", this.productController.handleError(req, new Exception("An error occurred")));
    }

    @Test
    void testDeleteProductById() throws Exception {
        doNothing().when(this.productService).deleteProductById((Integer) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));
    }

    @Test
    void testDeleteProductById2() throws Exception {
        doNothing().when(this.productService).deleteProductById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/delete")
                .param("id", "https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("error"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("error"));
    }

    @Test
    void testFilterProductsByTitleAndByMaxAndMinPrice() throws Exception {
        doNothing().when(this.productService)
                .pagination((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(),
                        (org.springframework.ui.Model) any(), (org.springframework.data.domain.Page<Product>) any());
        when(this.productService.findProductsByTitleAndByMaxAndMinPriceBySearchConditional(
                (com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(), (String) any(), (Integer) any(),
                (Integer) any())).thenReturn(new PageImpl<Product>(new ArrayList<Product>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/filter");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("productSearchCondition"))
                .andExpect(MockMvcResultMatchers.view().name("product/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/list"));
    }

    @Test
    void testFilterProductsByTitleAndByMaxAndMinPrice2() throws Exception {
        doNothing().when(this.productService)
                .pagination((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(),
                        (org.springframework.ui.Model) any(), (org.springframework.data.domain.Page<Product>) any());
        when(this.productService.findProductsByTitleAndByMaxAndMinPriceBySearchConditional(
                (com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(), (String) any(), (Integer) any(),
                (Integer) any())).thenReturn(new PageImpl<Product>(new ArrayList<Product>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/filter");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("productSearchCondition"))
                .andExpect(MockMvcResultMatchers.view().name("product/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/list"));
    }

    @Test
    void testGetListProducts() throws Exception {
        doNothing().when(this.productService)
                .pagination((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(),
                        (org.springframework.ui.Model) any(), (org.springframework.data.domain.Page<Product>) any());
        when(this.productService
                .findAllBySearchConditional((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any()))
                .thenReturn(new PageImpl<Product>(new ArrayList<Product>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/list");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("productSearchCondition"))
                .andExpect(MockMvcResultMatchers.view().name("product/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/list"));
    }

    @Test
    void testGetListProducts2() throws Exception {
        doNothing().when(this.productService)
                .pagination((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(),
                        (org.springframework.ui.Model) any(), (org.springframework.data.domain.Page<Product>) any());
        when(this.productService
                .findAllBySearchConditional((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any()))
                .thenReturn(new PageImpl<Product>(new ArrayList<Product>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/list");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("productSearchCondition"))
                .andExpect(MockMvcResultMatchers.view().name("product/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/list"));
    }

    @Test
    void testGetProductForm() throws Exception {
        when(this.categoryService.findCategories()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/form");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "error", "product"))
                .andExpect(MockMvcResultMatchers.view().name("product/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/form"));
    }

    @Test
    void testGetProductForm2() throws Exception {
        when(this.productService.findProductDtoById((Integer) any())).thenReturn(new ProductDto());
        when(this.categoryService.findCategories()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product/form");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories", "error", "product"))
                .andExpect(MockMvcResultMatchers.view().name("product/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/form"));
    }

    @Test
    void testGetProductForm3() throws Exception {
        when(this.productService.findProductDtoById((Integer) any())).thenReturn(new ProductDto());
        when(this.categoryService.findCategories()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/form")
                .param("id", "https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("error"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("error"));
    }

    @Test
    void testGetProductsByCategoryId() throws Exception {
        doNothing().when(this.productService)
                .pagination((com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any(),
                        (org.springframework.ui.Model) any(), (org.springframework.data.domain.Page<Product>) any());
        when(this.productService.findProductsByCategoryId((Integer) any(),
                (com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition) any()))
                .thenReturn(new PageImpl<Product>(new ArrayList<Product>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/list/{catId}", 123);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("product/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("product/list"));
    }

    @Test
    void testSaveProduct() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product/form");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("error"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("error"));
    }

    @Test
    void testSaveProduct2() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/product/form");
        postResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("error"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("error"));
    }
}

