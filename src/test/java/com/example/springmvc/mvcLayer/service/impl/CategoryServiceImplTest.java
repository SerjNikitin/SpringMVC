package com.example.springmvc.mvcLayer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void testFindCategories() {
        ArrayList<Category> categoryList = new ArrayList<Category>();
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> actualFindCategoriesResult = this.categoryServiceImpl.findCategories();
        assertSame(categoryList, actualFindCategoriesResult);
        assertTrue(actualFindCategoriesResult.isEmpty());
        verify(this.categoryRepository).findAll();
    }

    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setId(1);
        category.setParentCategory(new Category());
        category.setSubCategories(new HashSet<Category>());
        category.setProducts(new ArrayList<Product>());
        category.setTitle("Dr");

        Category category1 = new Category();
        category1.setId(1);
        category1.setParentCategory(category);
        category1.setSubCategories(new HashSet<Category>());
        category1.setProducts(new ArrayList<Product>());
        category1.setTitle("Dr");

        Category category2 = new Category();
        category2.setId(1);
        category2.setParentCategory(category1);
        category2.setSubCategories(new HashSet<Category>());
        category2.setProducts(new ArrayList<Product>());
        category2.setTitle("Dr");

        Category category3 = new Category();
        category3.setId(1);
        category3.setParentCategory(category2);
        category3.setSubCategories(new HashSet<Category>());
        category3.setProducts(new ArrayList<Product>());
        category3.setTitle("Dr");
        Optional<Category> ofResult = Optional.<Category>of(category3);
        when(this.categoryRepository.findCategoryByTitle((String) any())).thenReturn(ofResult);
        this.categoryServiceImpl.addCategory("Dr");
        verify(this.categoryRepository).findCategoryByTitle((String) any());
        assertTrue(this.categoryServiceImpl.findCategories().isEmpty());
    }

    @Test
    void testAddCategory2() {
        Category category = new Category();
        category.setId(1);
        category.setParentCategory(new Category());
        category.setSubCategories(new HashSet<Category>());
        category.setProducts(new ArrayList<Product>());
        category.setTitle("Dr");

        Category category1 = new Category();
        category1.setId(1);
        category1.setParentCategory(category);
        category1.setSubCategories(new HashSet<Category>());
        category1.setProducts(new ArrayList<Product>());
        category1.setTitle("Dr");

        Category category2 = new Category();
        category2.setId(1);
        category2.setParentCategory(category1);
        category2.setSubCategories(new HashSet<Category>());
        category2.setProducts(new ArrayList<Product>());
        category2.setTitle("Dr");

        Category category3 = new Category();
        category3.setId(1);
        category3.setParentCategory(category2);
        category3.setSubCategories(new HashSet<Category>());
        category3.setProducts(new ArrayList<Product>());
        category3.setTitle("Dr");

        Category category4 = new Category();
        category4.setId(1);
        category4.setParentCategory(category3);
        category4.setSubCategories(new HashSet<Category>());
        category4.setProducts(new ArrayList<Product>());
        category4.setTitle("Dr");
        when(this.categoryRepository.save((Category) any())).thenReturn(category4);
        when(this.categoryRepository.findCategoryByTitle((String) any())).thenReturn(Optional.<Category>empty());
        this.categoryServiceImpl.addCategory("Dr");
        verify(this.categoryRepository).findCategoryByTitle((String) any());
        verify(this.categoryRepository).save((Category) any());
        assertTrue(this.categoryServiceImpl.findCategories().isEmpty());
    }

    @Test
    void testFindCategoryById() {
        HashSet<Category> categorySet = new HashSet<Category>();
        when(this.categoryRepository.findCategoryByIdIn((Set<Integer>) any())).thenReturn(categorySet);
        Set<Category> actualFindCategoryByIdResult = this.categoryServiceImpl.findCategoryById(new HashSet<Integer>());
        assertSame(categorySet, actualFindCategoryByIdResult);
        assertTrue(actualFindCategoryByIdResult.isEmpty());
        verify(this.categoryRepository).findCategoryByIdIn((Set<Integer>) any());
        assertTrue(this.categoryServiceImpl.findCategories().isEmpty());
    }

    @Test
    void testGetCategoryIdList() {
        assertTrue(this.categoryServiceImpl.getCategoryIdList(new HashSet<Category>()).isEmpty());
    }

    @Test
    void testGetCategoryIdList2() {
        Category category = new Category();
        category.setId(1);
        category.setParentCategory(new Category());
        category.setSubCategories(new HashSet<Category>());
        category.setProducts(new ArrayList<Product>());
        category.setTitle("Dr");

        Category category1 = new Category();
        category1.setId(1);
        category1.setParentCategory(category);
        category1.setSubCategories(new HashSet<Category>());
        category1.setProducts(new ArrayList<Product>());
        category1.setTitle("Dr");

        Category category2 = new Category();
        category2.setId(1);
        category2.setParentCategory(category1);
        category2.setSubCategories(new HashSet<Category>());
        category2.setProducts(new ArrayList<Product>());
        category2.setTitle("Dr");

        Category category3 = new Category();
        category3.setId(1);
        category3.setParentCategory(category2);
        category3.setSubCategories(new HashSet<Category>());
        category3.setProducts(new ArrayList<Product>());
        category3.setTitle("Dr");

        HashSet<Category> categorySet = new HashSet<Category>();
        categorySet.add(category3);
        Set<Integer> actualCategoryIdList = this.categoryServiceImpl.getCategoryIdList(categorySet);
        assertEquals(1, actualCategoryIdList.size());
        assertTrue(actualCategoryIdList.contains(1));
    }

    @Test
    void testGetCategoryIdList3() {
        Category category = new Category();
        category.setId(1);
        category.setParentCategory(new Category());
        category.setSubCategories(new HashSet<Category>());
        category.setProducts(new ArrayList<Product>());
        category.setTitle("Dr");

        Category category1 = new Category();
        category1.setId(1);
        category1.setParentCategory(category);
        category1.setSubCategories(new HashSet<Category>());
        category1.setProducts(new ArrayList<Product>());
        category1.setTitle("Dr");

        Category category2 = new Category();
        category2.setId(1);
        category2.setParentCategory(category1);
        category2.setSubCategories(new HashSet<Category>());
        category2.setProducts(new ArrayList<Product>());
        category2.setTitle("Dr");

        Category category3 = new Category();
        category3.setId(1);
        category3.setParentCategory(category2);
        category3.setSubCategories(new HashSet<Category>());
        category3.setProducts(new ArrayList<Product>());
        category3.setTitle("Dr");

        Category category4 = new Category();
        category4.setId(1);
        category4.setParentCategory(new Category());
        category4.setSubCategories(new HashSet<Category>());
        category4.setProducts(new ArrayList<Product>());
        category4.setTitle("Title");

        Category category5 = new Category();
        category5.setId(1);
        category5.setParentCategory(category4);
        category5.setSubCategories(new HashSet<Category>());
        category5.setProducts(new ArrayList<Product>());
        category5.setTitle("Dr");

        Category category6 = new Category();
        category6.setId(1);
        category6.setParentCategory(category5);
        category6.setSubCategories(new HashSet<Category>());
        category6.setProducts(new ArrayList<Product>());
        category6.setTitle("Dr");

        Category category7 = new Category();
        category7.setId(1);
        category7.setParentCategory(category6);
        category7.setSubCategories(new HashSet<Category>());
        category7.setProducts(new ArrayList<Product>());
        category7.setTitle("Dr");

        HashSet<Category> categorySet = new HashSet<Category>();
        categorySet.add(category7);
        categorySet.add(category3);
        Set<Integer> actualCategoryIdList = this.categoryServiceImpl.getCategoryIdList(categorySet);
        assertEquals(1, actualCategoryIdList.size());
        assertTrue(actualCategoryIdList.contains(1));
    }

    @Test
    void testFindCategory() {
        Category category = new Category();
        category.setId(1);
        category.setParentCategory(new Category());
        category.setSubCategories(new HashSet<Category>());
        category.setProducts(new ArrayList<Product>());
        category.setTitle("Dr");

        Category category1 = new Category();
        category1.setId(1);
        category1.setParentCategory(category);
        category1.setSubCategories(new HashSet<Category>());
        category1.setProducts(new ArrayList<Product>());
        category1.setTitle("Dr");

        Category category2 = new Category();
        category2.setId(1);
        category2.setParentCategory(category1);
        category2.setSubCategories(new HashSet<Category>());
        category2.setProducts(new ArrayList<Product>());
        category2.setTitle("Dr");

        Category category3 = new Category();
        category3.setId(1);
        category3.setParentCategory(category2);
        category3.setSubCategories(new HashSet<Category>());
        category3.setProducts(new ArrayList<Product>());
        category3.setTitle("Dr");
        Optional<Category> ofResult = Optional.<Category>of(category3);
        when(this.categoryRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Category> actualFindCategoryResult = this.categoryServiceImpl.findCategory(1);
        assertSame(ofResult, actualFindCategoryResult);
        assertTrue(actualFindCategoryResult.isPresent());
        verify(this.categoryRepository).findById((Integer) any());
        assertTrue(this.categoryServiceImpl.findCategories().isEmpty());
    }
}

