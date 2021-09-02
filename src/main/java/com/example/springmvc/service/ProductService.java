package com.example.springmvc.service;

import com.example.springmvc.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveWithImage(Product product, MultipartFile image);

    List<Product> findProducts();

    Optional<Product> findProductById(Integer id);

    RedirectView saveProduct(Integer categoryId, String price, String title,
                             RedirectAttributes attributes,MultipartFile image);

    RedirectView updateProductById(Product product, String title, Integer categoryId, String price,
                                   RedirectAttributes attributes,MultipartFile image);

    void deleteProductById(Integer id);

    List<Product> findProductByCategoryId(Integer categoryId);

    List<Product> findProductsByTitleAndByMaxAndMinPrice(String title, Integer minPrice, Integer maxPrice);
}