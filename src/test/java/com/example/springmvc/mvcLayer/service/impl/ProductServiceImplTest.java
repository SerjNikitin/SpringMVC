package com.example.springmvc.mvcLayer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition;
import com.example.springmvc.mvcLayer.repository.ProductRepository;
import com.example.springmvc.mvcLayer.service.CategoryService;
import com.example.springmvc.mvcLayer.service.FileService;
import com.example.springmvc.mvcLayer.service.ReviewService;

import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private CategoryService categoryService;

    @MockBean
    private FileService fileService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private ReviewService reviewService;

    @Test
    void testSaveProductAndImage() throws UnsupportedEncodingException {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.fileService.saveProductImage((org.springframework.web.multipart.MultipartFile) any()))
                .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
        when(this.categoryService.findCategoryById((Set<Integer>) any())).thenReturn(new HashSet<Category>());
        ProductDto productDto = new ProductDto();
        Product actualSaveProductAndImageResult = this.productServiceImpl.saveProductAndImage(productDto,
                new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));
        assertSame(product, actualSaveProductAndImageResult);
        String expectedImage = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString();
        assertEquals(expectedImage, actualSaveProductAndImageResult.getImage());
        verify(this.productRepository, atLeast(1)).save((Product) any());
        verify(this.fileService).saveProductImage((org.springframework.web.multipart.MultipartFile) any());
        verify(this.categoryService).findCategoryById((Set<Integer>) any());
    }

    @Test
    void testSaveProductAndImage2() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.fileService.saveProductImage((org.springframework.web.multipart.MultipartFile) any())).thenReturn(null);
        when(this.categoryService.findCategoryById((Set<Integer>) any())).thenReturn(new HashSet<Category>());
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getCountProduct()).thenReturn(3);
        when(productDto.getCategoryDto()).thenReturn(new HashSet<Integer>());
        when(productDto.getPrice()).thenReturn(1);
        when(productDto.getTitle()).thenReturn("Dr");
        when(productDto.getId()).thenReturn(1);
        assertSame(product,
                this.productServiceImpl.saveProductAndImage(productDto, new MockMultipartFile("Name", (byte[]) null)));
        verify(this.productRepository).save((Product) any());
        verify(this.categoryService).findCategoryById((Set<Integer>) any());
        verify(productDto).getCategoryDto();
        verify(productDto).getCountProduct();
        verify(productDto, atLeast(1)).getId();
        verify(productDto).getPrice();
        verify(productDto).getTitle();
    }

    @Test
    void testSaveProductAndImage3() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.fileService.saveProductImage((MultipartFile) any())).thenReturn(null);
        when(this.categoryService.findCategoryById((Set<Integer>) any())).thenReturn(new HashSet<Category>());
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getCountProduct()).thenReturn(3);
        when(productDto.getCategoryDto()).thenReturn(new HashSet<Integer>());
        when(productDto.getPrice()).thenReturn(1);
        when(productDto.getTitle()).thenReturn("Dr");
        when(productDto.getId()).thenReturn(1);
        assertSame(product, this.productServiceImpl.saveProductAndImage(productDto, null));
        verify(this.productRepository).save((Product) any());
        verify(this.categoryService).findCategoryById((Set<Integer>) any());
        verify(productDto).getCategoryDto();
        verify(productDto).getCountProduct();
        verify(productDto, atLeast(1)).getId();
        verify(productDto).getPrice();
        verify(productDto).getTitle();
    }

    @Test
    void testSaveProductAndImage4() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.fileService.saveProductImage((MultipartFile) any())).thenReturn(null);
        when(this.categoryService.findCategoryById((Set<Integer>) any())).thenReturn(new HashSet<Category>());
        ProductDto productDto = mock(ProductDto.class);
        when(productDto.getCountProduct()).thenReturn(3);
        when(productDto.getCategoryDto()).thenReturn(new HashSet<Integer>());
        when(productDto.getPrice()).thenReturn(1);
        when(productDto.getTitle()).thenReturn("Dr");
        when(productDto.getId()).thenReturn(1);
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.isEmpty()).thenReturn(true);
        assertSame(product, this.productServiceImpl.saveProductAndImage(productDto, multipartFile));
        verify(this.productRepository).save((Product) any());
        verify(this.categoryService).findCategoryById((Set<Integer>) any());
        verify(productDto).getCategoryDto();
        verify(productDto).getCountProduct();
        verify(productDto, atLeast(1)).getId();
        verify(productDto).getPrice();
        verify(productDto).getTitle();
        verify(multipartFile).isEmpty();
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Product> actualFindProductByIdResult = this.productServiceImpl.findProductById(1);
        assertSame(ofResult, actualFindProductByIdResult);
        assertTrue(actualFindProductByIdResult.isPresent());
        verify(this.productRepository).findById((Integer) any());
    }

    @Test
    void testFindProductDtoById() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findById((Integer) any())).thenReturn(ofResult);
        when(this.categoryService.getCategoryIdList((Set<Category>) any())).thenReturn(new HashSet<Integer>());
        ProductDto actualFindProductDtoByIdResult = this.productServiceImpl.findProductDtoById(1);
        assertTrue(actualFindProductDtoByIdResult.getCategoryDto().isEmpty());
        assertEquals("Dr", actualFindProductDtoByIdResult.getTitle());
        assertEquals(1, actualFindProductDtoByIdResult.getPrice().intValue());
        assertEquals(1, actualFindProductDtoByIdResult.getId().intValue());
        assertEquals(3, actualFindProductDtoByIdResult.getCountProduct().intValue());
        verify(this.productRepository).findById((Integer) any());
        verify(this.categoryService).getCategoryIdList((Set<Category>) any());
    }

    @Test
    void testFindProductDtoById2() {
        when(this.productRepository.findById((Integer) any())).thenReturn(Optional.<Product>empty());
        when(this.categoryService.getCategoryIdList((java.util.Set<com.example.springmvc.mvcLayer.domain.Category>) any()))
                .thenReturn(new HashSet<Integer>());
        assertThrows(NoSuchElementException.class, () -> this.productServiceImpl.findProductDtoById(1));
        verify(this.productRepository).findById((Integer) any());
    }

    @Test
    void testUpdateCountInProduct() {
        doNothing().when(this.productRepository).updateCount((Integer) any(), (Integer) any());
        this.productServiceImpl.updateCountInProduct(1, 3);
        verify(this.productRepository).updateCount((Integer) any(), (Integer) any());
    }

    @Test
    void testDeleteProductById() {
        doNothing().when(this.reviewService).deleteAllReviewByProductId((Integer) any());
        doNothing().when(this.productRepository).deleteById((Integer) any());
        this.productServiceImpl.deleteProductById(1);
        verify(this.reviewService).deleteAllReviewByProductId((Integer) any());
        verify(this.productRepository).deleteById((Integer) any());
    }

    @Test
    void testFindProductsByCategoryId() {
        PageImpl<Product> pageImpl = new PageImpl<Product>(new ArrayList<Product>());
        when(this.productRepository.findProductsByCategories((org.springframework.data.domain.Pageable) any(),
                (Category) any())).thenReturn(pageImpl);

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
        when(this.categoryService.findCategory((Integer) any())).thenReturn(ofResult);

        ProductSearchCondition productSearchCondition = new ProductSearchCondition();
        productSearchCondition.setPageSize(3);
        productSearchCondition.setSortField("Sort Field");
        productSearchCondition.setPageNum(10);
        productSearchCondition.setSortDirection(Sort.Direction.ASC);
        Page<Product> actualFindProductsByCategoryIdResult = this.productServiceImpl.findProductsByCategoryId(123,
                productSearchCondition);
        assertSame(pageImpl, actualFindProductsByCategoryIdResult);
        assertTrue(actualFindProductsByCategoryIdResult.toList().isEmpty());
        verify(this.productRepository).findProductsByCategories((org.springframework.data.domain.Pageable) any(),
                (Category) any());
        verify(this.categoryService).findCategory((Integer) any());
    }

    @Test
    void testFindAllBySearchConditional() {
        PageImpl<Product> pageImpl = new PageImpl<Product>(new ArrayList<Product>());
        when(this.productRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        ProductSearchCondition productSearchCondition = new ProductSearchCondition();
        productSearchCondition.setPageSize(3);
        productSearchCondition.setSortField("Sort Field");
        productSearchCondition.setPageNum(10);
        productSearchCondition.setSortDirection(Sort.Direction.ASC);
        Page<Product> actualFindAllBySearchConditionalResult = this.productServiceImpl
                .findAllBySearchConditional(productSearchCondition);
        assertSame(pageImpl, actualFindAllBySearchConditionalResult);
        assertTrue(actualFindAllBySearchConditionalResult.toList().isEmpty());
        verify(this.productRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindAllBySearchConditional2() {
        PageImpl<Product> pageImpl = new PageImpl<Product>(new ArrayList<Product>());
        when(this.productRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        ProductSearchCondition productSearchCondition = new ProductSearchCondition();
        productSearchCondition.setPageSize(1);
        productSearchCondition.setSortField("Sort Field");
        productSearchCondition.setPageNum(10);
        productSearchCondition.setSortDirection(Sort.Direction.ASC);
        Page<Product> actualFindAllBySearchConditionalResult = this.productServiceImpl
                .findAllBySearchConditional(productSearchCondition);
        assertSame(pageImpl, actualFindAllBySearchConditionalResult);
        assertTrue(actualFindAllBySearchConditionalResult.toList().isEmpty());
        verify(this.productRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindProductsByTitleAndByMaxAndMinPriceBySearchConditional() {
        PageImpl<Product> pageImpl = new PageImpl<Product>(new ArrayList<Product>());
        when(this.productRepository.findProductsByTitleContainingIgnoreCaseAndPriceBetween((String) any(), (Integer) any(),
                (Integer) any(), (org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        ProductSearchCondition productSearchCondition = new ProductSearchCondition();
        productSearchCondition.setPageSize(3);
        productSearchCondition.setSortField("Sort Field");
        productSearchCondition.setPageNum(10);
        productSearchCondition.setSortDirection(Sort.Direction.ASC);
        Page<Product> actualFindProductsByTitleAndByMaxAndMinPriceBySearchConditionalResult = this.productServiceImpl
                .findProductsByTitleAndByMaxAndMinPriceBySearchConditional(productSearchCondition, "Dr", 1, 3);
        assertSame(pageImpl, actualFindProductsByTitleAndByMaxAndMinPriceBySearchConditionalResult);
        assertTrue(actualFindProductsByTitleAndByMaxAndMinPriceBySearchConditionalResult.toList().isEmpty());
        verify(this.productRepository).findProductsByTitleContainingIgnoreCaseAndPriceBetween((String) any(),
                (Integer) any(), (Integer) any(), (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindProductsByTitleAndByMaxAndMinPriceBySearchConditional2() {
        PageImpl<Product> pageImpl = new PageImpl<Product>(new ArrayList<Product>());
        when(this.productRepository.findProductsByTitleContainingIgnoreCaseAndPriceBetween((String) any(), (Integer) any(),
                (Integer) any(), (org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        ProductSearchCondition productSearchCondition = new ProductSearchCondition();
        productSearchCondition.setPageSize(1);
        productSearchCondition.setSortField("Sort Field");
        productSearchCondition.setPageNum(10);
        productSearchCondition.setSortDirection(Sort.Direction.ASC);
        Page<Product> actualFindProductsByTitleAndByMaxAndMinPriceBySearchConditionalResult = this.productServiceImpl
                .findProductsByTitleAndByMaxAndMinPriceBySearchConditional(productSearchCondition, "Dr", 1, 3);
        assertSame(pageImpl, actualFindProductsByTitleAndByMaxAndMinPriceBySearchConditionalResult);
        assertTrue(actualFindProductsByTitleAndByMaxAndMinPriceBySearchConditionalResult.toList().isEmpty());
        verify(this.productRepository).findProductsByTitleContainingIgnoreCaseAndPriceBetween((String) any(),
                (Integer) any(), (Integer) any(), (org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testPagination() {
        ProductSearchCondition productSearchCondition = new ProductSearchCondition();
        productSearchCondition.setPageSize(3);
        productSearchCondition.setSortField("Sort Field");
        productSearchCondition.setPageNum(10);
        productSearchCondition.setSortDirection(Sort.Direction.ASC);
        ConcurrentModel concurrentModel = new ConcurrentModel();
        this.productServiceImpl.pagination(productSearchCondition, concurrentModel,
                new PageImpl<Product>(new ArrayList<Product>()));
        assertEquals(10, productSearchCondition.getPageNum());
        assertEquals("Sort Field", productSearchCondition.getSortField());
        assertEquals(Sort.Direction.ASC, productSearchCondition.getSortDirection());
        assertEquals(3, productSearchCondition.getPageSize().intValue());
        assertTrue(((PageImpl) concurrentModel.get("page")).toList().isEmpty());
    }
}

