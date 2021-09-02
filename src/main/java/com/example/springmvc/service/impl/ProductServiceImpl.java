package com.example.springmvc.service.impl;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import com.example.springmvc.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

//    private final CategoryService categoryService;
//
//    private final ProductRepository productRepository;

//    @Override
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Product findById(Integer id) {
//        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//    }
//
//    @Override
//    public Product save(Product productEntity) {
//        return productRepository.save(productEntity);
//    }
//
//    @Override
//    public Page<Product> findAllByPageAndCategory(Pageable pageable, String categoryAlias) {
//        if (StringUtils.isNotBlank(categoryAlias)) {
//            Category category = categoryService.findByAlias(categoryAlias);
//            return productRepository.findAllByCategories(category, pageable);
//        }
//
//        return productRepository.findAll(pageable);
//    }
//
//    @Override
//    @Transactional
//    public Product saveWithImage(Product product, MultipartFile image) {
//        Product savedProduct = productRepository.save(product);
//
//        if (image != null && !image.isEmpty()) {
//            Path pathImage = FileUtils.saveProductImage(image);
//            savedProduct.setImage(pathImage.toString());
//
//            productRepository.save(savedProduct);
//        }
//
//        return savedProduct;
//    }
//
//    @Override
//    public void deleteById(Long productId) {
//        productRepository.deleteById(Math.toIntExact(productId));
//    }

    private final ProductRepository productRepository;

    private final CategoryService categoryService;


    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }


    @Override
    @Transactional
    public Product saveWithImage(Product product, MultipartFile image) {
        Product savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImage(pathImage.toString());

            productRepository.save(savedProduct);
        }

        return savedProduct;
    }

//    @Override
//    @Transactional
//    public RedirectView saveWithImage(MultipartFile image, Integer categoryId,String title,
//                                 String price,RedirectAttributes attributes,String id) {
//
//        Optional<Category> category = categoryService.findCategoryById(categoryId);
//        if (category.isPresent()) {
//
//            Product product = productService.findProductById(Integer.parseInt(id)).get();
//            product.setCategory(category.get());
//            product.setImage(image.toString());
//            product.setTitle(title);
//            product.setPrice(Integer.parseInt(price));
//
//            Product savedProduct = productRepository.save(product);
//
//            if (image != null && !image.isEmpty()) {
//                Path pathImage = FileUtils.saveProductImage(image);
//                savedProduct.setImage(pathImage.toString());
//
//                productRepository.save(savedProduct);
//            }
//
//        }
//        return new RedirectView("/product");
//    }

    @Transactional
    @Override
    public RedirectView saveProduct(Integer categoryId, String price, String title, RedirectAttributes attributes,
                                    MultipartFile image) {
        if (title.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле названия продукта");
            return new RedirectView("/product/add-product");
        }
        if (price.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле цены продукта");
            return new RedirectView("/product/add-product");
        }
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {
            int i = Integer.parseInt(price);
            Product product = new Product(title, i);
            product.setCategory(category.get());
            Product savedProduct = productRepository.save(product);

            if (image != null && !image.isEmpty()) {
                Path pathImage = FileUtils.saveProductImage(image);
                savedProduct.setImage(pathImage.toString());
                productRepository.save(savedProduct);
            }
        }
        return new RedirectView("/product");
    }

//    @Transactional
//    @Override
//    public RedirectView updateProductById(Product product, Integer categoryId, String price,
//                                          RedirectAttributes attributes, MultipartFile image) {
//        if (product.getTitle().isEmpty()) {
//            attributes.addFlashAttribute("error", "Заполните поле названия продукта");
//            return new RedirectView("/product/update/{id}");
//        }
//        if (price.isEmpty()) {
//            attributes.addFlashAttribute("error", "Заполните поле цены продукта");
//            return new RedirectView("/product/update/{id}");
//        }
//        Optional<Category> category = categoryService.findCategoryById(categoryId);
//        if (category.isPresent()) {
//            product.setCategory(category.get());
//            Product save = productRepository.save(product);
//
//            if (image != null && !image.isEmpty()) {
//                Path pathImage = FileUtils.saveProductImage(image);
//                save.setImage(pathImage.toString());
//                productRepository.save(save);
//            }
//        }
//        return new RedirectView("/product");
//    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByCategoryId(Integer categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByTitleAndByMaxAndMinPrice(String title, Integer minPrice, Integer maxPrice) {
        if (Objects.isNull(minPrice)) {
            minPrice = 0;
        }
        if (Objects.isNull(maxPrice)) {
            maxPrice = Integer.MAX_VALUE;
        }
        return productRepository.findProductsByTitleContainingAndPriceBetween(title, minPrice, maxPrice);
    }
}