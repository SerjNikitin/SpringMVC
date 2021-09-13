package com.example.springmvc.service;

import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.search.ProductSearchCondition;
import com.example.springmvc.domain.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Page<Product> findAllBySearchConditional(ProductSearchCondition searchCondition);

    Product saveProduct(Product product);

    Product saveProductAndImage(ProductDto product, MultipartFile image);

    List<Product> findProducts();

    Optional<Product> findProductById(Integer id);

    ProductDto findProductDtoById(Integer id);

    void deleteProductById(Integer id);

    List<Product> findProductsByTitleAndByMaxAndMinPrice(String title, Integer minPrice, Integer maxPrice);

    Set<ProductDto> findProductsDtoByCategoryId(Integer categoryId);
}