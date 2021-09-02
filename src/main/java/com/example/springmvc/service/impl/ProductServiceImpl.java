package com.example.springmvc.service.impl;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.repository.ProductRepository;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import com.example.springmvc.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

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
//
//        if (image != null && !image.isEmpty()) {
//            Path pathImage = FileUtils.saveProductImage(image);
//            savedProduct.setImage(pathImage.toString());
//
//            productRepository.save(savedProduct);
//        }
        return savedProduct;
    }

    @Transactional
    @Override
    public RedirectView saveProduct(Integer categoryId, String price, String title, RedirectAttributes attributes,
                                    MultipartFile image) {
        if (getRedirectView(title, price, attributes, image)) return new RedirectView("/product/add-product");

        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {
            int i = Integer.parseInt(price);
            Product product = new Product(title, i);
            product.setCategory(category.get());
            functionForUpdateAndSave(product, image);
        }
        return new RedirectView("/product");
    }

    @Transactional
    @Override
    public RedirectView updateProductById(Product product, String title, Integer categoryId, String price,
                                          RedirectAttributes attributes, MultipartFile image) {
        if (getRedirectView(title, price, attributes, image)) return new RedirectView("/product/update/{id}");

        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {
            product.setPrice(Integer.parseInt(price));
            product.setTitle(title);
            product.setCategory(category.get());
            functionForUpdateAndSave(product, image);
        }
        return new RedirectView("/product");
    }

    private void functionForUpdateAndSave(Product product, MultipartFile image) {
        Product savedProduct = productRepository.save(product);

        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            savedProduct.setImage(pathImage.toString());
            productRepository.save(savedProduct);
        }
    }

    private boolean getRedirectView(String title, String price, RedirectAttributes attributes, MultipartFile image) {
        if (title.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле с названием продукта");
            return true;
        }
        if (price.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле с ценой продукта");
            return true;
        }
        if (image.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле с фотографией продукта");
            return true;
        }
        return false;
    }

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