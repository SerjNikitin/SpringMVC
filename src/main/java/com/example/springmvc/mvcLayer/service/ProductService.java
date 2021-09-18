package com.example.springmvc.mvcLayer.service;

import com.example.springmvc.mvcLayer.domain.productMarket.Product;
import com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Page<Product> findAllBySearchConditional(ProductSearchCondition searchCondition);

    Product saveProduct(Product product);

    Product saveProductAndImage(ProductDto product, MultipartFile image);

    Optional<Product> findProductById(Integer id);

    ProductDto findProductDtoById(Integer id);

    void deleteProductById(Integer id);

    Set<ProductDto> findProductsDtoByCategoryId(Integer categoryId);

    Page<Product> findProductsByTitleAndByMaxAndMinPriceBySearchConditional(
            ProductSearchCondition searchCondition, String title, Integer minPrice, Integer maxPrice);

    void pagination(ProductSearchCondition searchCondition, Model model, Page<Product> page);
}