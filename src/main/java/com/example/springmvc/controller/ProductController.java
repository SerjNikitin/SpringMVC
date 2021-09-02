package com.example.springmvc.controller;


import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @GetMapping("/add-product")
    public String addFormToAddProduct(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("product", new Product());
        model.addAttribute("error", error);
        model.addAttribute("category", categoryService.findCategory());
        return "product/addProduct";
    }

    @PostMapping("/add-product")
    public RedirectView addProduct(@RequestParam String title, @RequestParam String price,
                                   @RequestParam(required = false) MultipartFile image,
                                   @RequestParam Integer categoryId, RedirectAttributes attributes) {
        return productService.saveProduct(categoryId, price, title, attributes, image);
    }

    @GetMapping("/update/{id}")
    public String openViewUpdateProductById(@PathVariable Integer id,
                                            @ModelAttribute("error") String error, Model model) {
        Product product = productService.findProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("error", error);
        model.addAttribute("categories", categoryService.findCategory());
        return "product/updateProduct";
    }

    @PostMapping("/update/{id}")
    public RedirectView updateProductById(@RequestParam Integer categoryId, @RequestParam(required = false) MultipartFile image,
                                          @RequestParam String title, @RequestParam String price, RedirectAttributes attributes,
                                          @PathVariable String id) {

        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {

            Product product = productService.findProductById(Integer.parseInt(id)).get();
            product.setCategory(category.get());
            product.setImage(image.toString());
            product.setTitle(title);
            product.setPrice(Integer.parseInt(price));

             productService.saveWithImage(product,image);
        }
        return new RedirectView("/product");
    }

//
//    @GetMapping("/add-product")
//    public String addFormToAddProduct(Model model, @ModelAttribute("error") String error) {
//        model.addAttribute("product", new Product());
//        model.addAttribute("error", error);
//        model.addAttribute("category", categoryService.findCategory());
//        return "product/addProduct";
//    }
//
//    @PostMapping("/add-product")
//    public RedirectView addProduct(@RequestParam String title, @RequestParam String price,
//                                   @RequestParam(required = false) MultipartFile image,
//                                   @RequestParam Integer categoryId, RedirectAttributes attributes) {
//
//        return productService.saveProduct(categoryId, price, title, attributes, image);
//    }
//
//    @GetMapping("/update/{id}")
//    public String openViewUpdateProductById(@PathVariable Integer id,
//                                            @ModelAttribute("error") String error, Model model) {
//        Product product= productService.findProductById(id).get();
//        model.addAttribute("product", product);
//        model.addAttribute("error", error);
//        model.addAttribute("categories", categoryService.findCategory());
//        return "product/updateProduct";
//    }

//    @PostMapping("/update/{id}")
//    public RedirectView updateProductById(@RequestParam Integer categoryId, @RequestParam String price,
//                                          @ModelAttribute Product product, RedirectAttributes attributes,
//                                          @RequestParam MultipartFile image) {
//        return productService.saveProduct(categoryId,price, product.getTitle(),attributes, image);
////        return productService.updateProductById(product, categoryId, price, attributes, image);
//    }

    @GetMapping
    private String getAllProduct(Model model) {
        model.addAttribute("category", categoryService.findCategory());
        model.addAttribute("products", productService.findProducts());
        return "product/getAllProduct";
    }

    @GetMapping("/findById")
    public String findProductById(@RequestParam Integer id, Model model) {
        Optional<Product> productById = productService.findProductById(id);
        List<Category> category = categoryService.findCategory();
        model.addAttribute("category", category);
        if (productById.isPresent()) {
            model.addAttribute("products", Collections.singletonList(productById.get()));
        } else {
            model.addAttribute("products", Collections.emptyList());
        }
        return "product/getProduct";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return new RedirectView("/product");
    }

    @GetMapping("/filter")
    public String filterProductsByTitleAndByMaxAndMinPrice(@RequestParam(required = false) String title,
                                                           @RequestParam(required = false) Integer minPrice,
                                                           @RequestParam(required = false) Integer maxPrice, Model model) {
        List<Product> products = productService.findProductsByTitleAndByMaxAndMinPrice(title, minPrice, maxPrice);
        List<Category> category = categoryService.findCategory();
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return ("product/getAllProduct");
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);
        return "error";
    }
}