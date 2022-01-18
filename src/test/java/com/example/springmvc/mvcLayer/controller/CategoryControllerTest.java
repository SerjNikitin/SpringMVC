package com.example.springmvc.mvcLayer.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.service.CategoryService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testAddViewToCreateCategory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/form");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("category", "error"))
                .andExpect(MockMvcResultMatchers.view().name("category/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("category/form"));
    }

    @Test
    void testAddViewToCreateCategory2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/form", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("category", "error"))
                .andExpect(MockMvcResultMatchers.view().name("category/form"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("category/form"));
    }

    @Test
    void testCreateCategory() throws Exception {
        doNothing().when(this.categoryService).addCategory((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/category/form").param("title", "foo");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/category/list"));
    }

    @Test
    void testCreateCategory2() throws Exception {
        doNothing().when(this.categoryService).addCategory((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/category/form").param("title", "");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/category/form"));
    }

    @Test
    void testFindAllCategory() throws Exception {
        when(this.categoryService.findCategories()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/list");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("category/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("category/list"));
    }

    @Test
    void testFindAllCategory2() throws Exception {
        when(this.categoryService.findCategories()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/category/list");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
                .andExpect(MockMvcResultMatchers.view().name("category/list"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("category/list"));
    }
}

