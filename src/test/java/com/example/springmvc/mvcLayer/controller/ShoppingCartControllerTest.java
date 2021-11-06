package com.example.springmvc.mvcLayer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.domain.Category;
import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.cart.CartItem;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.repository.CategoryRepository;
import com.example.springmvc.mvcLayer.repository.ProductRepository;
import com.example.springmvc.mvcLayer.repository.ReviewRepository;
import com.example.springmvc.mvcLayer.repository.UserRepository;
import com.example.springmvc.mvcLayer.service.FileService;
import com.example.springmvc.mvcLayer.service.ProductService;
import com.example.springmvc.mvcLayer.service.RoleService;
import com.example.springmvc.mvcLayer.service.impl.CategoryServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.ProductServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.ReviewServiceImpl;
import com.example.springmvc.mvcLayer.service.impl.security.UserServiceImpl;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.view.RedirectView;

class ShoppingCartControllerTest {
    @Test
    void testGetCartList() {
        ProductRepository productRepository = mock(ProductRepository.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        assertEquals("cart/list", shoppingCartController.getCartList(new ShoppingCart()));
    }

    @Test
    void testAddProductToCart() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).updateCount((Integer) any(), (Integer) any());
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        ShoppingCart shoppingCart = new ShoppingCart();
        RedirectView actualAddProductToCartResult = shoppingCartController.addProductToCart(1, shoppingCart);
        assertFalse(actualAddProductToCartResult.isPropagateQueryProperties());
        assertFalse(actualAddProductToCartResult.isExposePathVariables());
        assertEquals("/product/list", actualAddProductToCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddProductToCartResult.getContentType());
        assertTrue(actualAddProductToCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Integer) any());
        verify(productRepository).updateCount((Integer) any(), (Integer) any());
        assertEquals(1, shoppingCart.getTotalPrice().intValue());
        assertEquals(1, shoppingCart.getCount());
        CartItem getResult = shoppingCart.getCartItems().get(1);
        assertEquals(1, getResult.getCount().intValue());
        ProductDto product1 = getResult.getProduct();
        assertTrue(product1.getCategoryDto().isEmpty());
        assertEquals("Dr", product1.getTitle());
        assertEquals(1, product1.getPrice().intValue());
        assertEquals(1, product1.getId().intValue());
        assertEquals(3, product1.getCountProduct().intValue());
    }

    @Test
    void testAddProductToCart2() {
        Product product = new Product();
        product.setCountProduct(0);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).updateCount((Integer) any(), (Integer) any());
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        RedirectView actualAddProductToCartResult = shoppingCartController.addProductToCart(1, new ShoppingCart());
        assertFalse(actualAddProductToCartResult.isPropagateQueryProperties());
        assertFalse(actualAddProductToCartResult.isExposePathVariables());
        assertEquals("/product/list", actualAddProductToCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddProductToCartResult.getContentType());
        assertTrue(actualAddProductToCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Integer) any());
    }

    @Test
    void testAddProductToCart3() {
        ProductDto productDto = new ProductDto();
        productDto.setCountProduct(0);
        ProductService productService = mock(ProductService.class);
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        RedirectView actualAddProductToCartResult = shoppingCartController.addProductToCart(1, new ShoppingCart());
        assertFalse(actualAddProductToCartResult.isPropagateQueryProperties());
        assertFalse(actualAddProductToCartResult.isExposePathVariables());
        assertEquals("/product/list", actualAddProductToCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddProductToCartResult.getContentType());
        assertTrue(actualAddProductToCartResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
    }

    @Test
    void testDeleteProductFromCart() {
        ProductRepository productRepository = mock(ProductRepository.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        RedirectView actualDeleteProductFromCartResult = shoppingCartController.deleteProductFromCart(1,
                new ShoppingCart());
        assertFalse(actualDeleteProductFromCartResult.isPropagateQueryProperties());
        assertFalse(actualDeleteProductFromCartResult.isExposePathVariables());
        assertEquals("/product/list", actualDeleteProductFromCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteProductFromCartResult.getContentType());
        assertTrue(actualDeleteProductFromCartResult.getAttributesMap().isEmpty());
    }

    @Test
    void testDeleteProductFromCart2() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).updateCount((Integer) any(), (Integer) any());
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.deleteCartItem((Integer) any())).thenReturn(true);
        RedirectView actualDeleteProductFromCartResult = shoppingCartController.deleteProductFromCart(1, shoppingCart);
        assertFalse(actualDeleteProductFromCartResult.isPropagateQueryProperties());
        assertFalse(actualDeleteProductFromCartResult.isExposePathVariables());
        assertEquals("/product/list", actualDeleteProductFromCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteProductFromCartResult.getContentType());
        assertTrue(actualDeleteProductFromCartResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Integer) any());
        verify(productRepository).updateCount((Integer) any(), (Integer) any());
        verify(shoppingCart).deleteCartItem((Integer) any());
    }

    @Test
    void testDeleteProductFromCart3() {
        ProductDto productDto = new ProductDto();
        productDto.setCountProduct(3);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).updateCountInProduct((Integer) any(), (Integer) any());
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.deleteCartItem((Integer) any())).thenReturn(true);
        RedirectView actualDeleteProductFromCartResult = shoppingCartController.deleteProductFromCart(1, shoppingCart);
        assertFalse(actualDeleteProductFromCartResult.isPropagateQueryProperties());
        assertFalse(actualDeleteProductFromCartResult.isExposePathVariables());
        assertEquals("/product/list", actualDeleteProductFromCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteProductFromCartResult.getContentType());
        assertTrue(actualDeleteProductFromCartResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
        verify(productService).updateCountInProduct((Integer) any(), (Integer) any());
        verify(shoppingCart).deleteCartItem((Integer) any());
    }

    @Test
    void testDeleteProductFromCart4() {
        ProductDto productDto = new ProductDto(123, "Dr", 1, new HashSet<Integer>(), 3);
        productDto.setCountProduct(3);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).updateCountInProduct((Integer) any(), (Integer) any());
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.deleteCartItem((Integer) any())).thenReturn(true);
        RedirectView actualDeleteProductFromCartResult = shoppingCartController.deleteProductFromCart(1, shoppingCart);
        assertFalse(actualDeleteProductFromCartResult.isPropagateQueryProperties());
        assertFalse(actualDeleteProductFromCartResult.isExposePathVariables());
        assertEquals("/product/list", actualDeleteProductFromCartResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteProductFromCartResult.getContentType());
        assertTrue(actualDeleteProductFromCartResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
        verify(productService).updateCountInProduct((Integer) any(), (Integer) any());
        verify(shoppingCart).deleteCartItem((Integer) any());
    }

    @Test
    void testAddSameItem() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).updateCount((Integer) any(), (Integer) any());
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        RedirectView actualAddSameItemResult = shoppingCartController.addSameItem(1, new ShoppingCart());
        assertFalse(actualAddSameItemResult.isPropagateQueryProperties());
        assertFalse(actualAddSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualAddSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddSameItemResult.getContentType());
        assertTrue(actualAddSameItemResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Integer) any());
        verify(productRepository).updateCount((Integer) any(), (Integer) any());
    }

    @Test
    void testAddSameItem2() {
        Product product = new Product();
        product.setCountProduct(0);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).updateCount((Integer) any(), (Integer) any());
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        RedirectView actualAddSameItemResult = shoppingCartController.addSameItem(1, new ShoppingCart());
        assertFalse(actualAddSameItemResult.isPropagateQueryProperties());
        assertFalse(actualAddSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualAddSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddSameItemResult.getContentType());
        assertTrue(actualAddSameItemResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Integer) any());
    }

    @Test
    void testAddSameItem3() {
        ProductDto productDto = new ProductDto();
        productDto.setCountProduct(3);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).updateCountInProduct((Integer) any(), (Integer) any());
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        RedirectView actualAddSameItemResult = shoppingCartController.addSameItem(1, new ShoppingCart());
        assertFalse(actualAddSameItemResult.isPropagateQueryProperties());
        assertFalse(actualAddSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualAddSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddSameItemResult.getContentType());
        assertTrue(actualAddSameItemResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
        verify(productService).updateCountInProduct((Integer) any(), (Integer) any());
    }

    @Test
    void testAddSameItem4() {
        ProductDto productDto = new ProductDto();
        productDto.setCountProduct(3);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).updateCountInProduct((Integer) any(), (Integer) any());
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        doNothing().when(shoppingCart).addSameItem((Integer) any());
        RedirectView actualAddSameItemResult = shoppingCartController.addSameItem(1, shoppingCart);
        assertFalse(actualAddSameItemResult.isPropagateQueryProperties());
        assertFalse(actualAddSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualAddSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualAddSameItemResult.getContentType());
        assertTrue(actualAddSameItemResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
        verify(productService).updateCountInProduct((Integer) any(), (Integer) any());
        verify(shoppingCart).addSameItem((Integer) any());
    }

    @Test
    void testDeleteSameItem() {
        Product product = new Product();
        product.setCountProduct(3);
        product.setId(1);
        product.setTitle("Dr");
        product.setCategories(new HashSet<Category>());
        product.setPrice(1);
        product.setImage("Image");
        Optional<Product> ofResult = Optional.<Product>of(product);
        ProductRepository productRepository = mock(ProductRepository.class);
        doNothing().when(productRepository).updateCount((Integer) any(), (Integer) any());
        when(productRepository.findById((Integer) any())).thenReturn(ofResult);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mock(CategoryRepository.class));
        FileService fileService = mock(FileService.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ShoppingCartController shoppingCartController = new ShoppingCartController(
                new ProductServiceImpl(productRepository, categoryService, fileService, new ReviewServiceImpl(reviewRepository,
                        new UserServiceImpl(mock(UserRepository.class), mock(RoleService.class), null))));
        ShoppingCart shoppingCart = new ShoppingCart();
        RedirectView actualDeleteSameItemResult = shoppingCartController.deleteSameItem(1, shoppingCart);
        assertFalse(actualDeleteSameItemResult.isPropagateQueryProperties());
        assertFalse(actualDeleteSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualDeleteSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteSameItemResult.getContentType());
        assertTrue(actualDeleteSameItemResult.getAttributesMap().isEmpty());
        verify(productRepository).findById((Integer) any());
        verify(productRepository).updateCount((Integer) any(), (Integer) any());
        assertEquals(0, shoppingCart.getTotalPrice().intValue());
    }

    @Test
    void testDeleteSameItem2() {
        ProductDto productDto = new ProductDto();
        productDto.setCountProduct(3);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).updateCountInProduct((Integer) any(), (Integer) any());
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        ShoppingCart shoppingCart = new ShoppingCart();
        RedirectView actualDeleteSameItemResult = shoppingCartController.deleteSameItem(1, shoppingCart);
        assertFalse(actualDeleteSameItemResult.isPropagateQueryProperties());
        assertFalse(actualDeleteSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualDeleteSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteSameItemResult.getContentType());
        assertTrue(actualDeleteSameItemResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
        verify(productService).updateCountInProduct((Integer) any(), (Integer) any());
        assertEquals(0, shoppingCart.getTotalPrice().intValue());
    }

    @Test
    void testDeleteSameItem3() {
        ProductDto productDto = new ProductDto();
        productDto.setCountProduct(3);
        ProductService productService = mock(ProductService.class);
        doNothing().when(productService).updateCountInProduct((Integer) any(), (Integer) any());
        when(productService.findProductDtoById((Integer) any())).thenReturn(productDto);
        ShoppingCartController shoppingCartController = new ShoppingCartController(productService);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        doNothing().when(shoppingCart).deleteSameItem((Integer) any());
        RedirectView actualDeleteSameItemResult = shoppingCartController.deleteSameItem(1, shoppingCart);
        assertFalse(actualDeleteSameItemResult.isPropagateQueryProperties());
        assertFalse(actualDeleteSameItemResult.isExposePathVariables());
        assertEquals("/cart", actualDeleteSameItemResult.getUrl());
        assertEquals("text/html;charset=ISO-8859-1", actualDeleteSameItemResult.getContentType());
        assertTrue(actualDeleteSameItemResult.getAttributesMap().isEmpty());
        verify(productService).findProductDtoById((Integer) any());
        verify(productService).updateCountInProduct((Integer) any(), (Integer) any());
        verify(shoppingCart).deleteSameItem((Integer) any());
    }
}

