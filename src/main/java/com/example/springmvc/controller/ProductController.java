package com.example.springmvc.controller;


import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        List<Category> category = categoryService.findCategory();
        model.addAttribute("category", category);
        return "addProduct";
    }

    @PostMapping("/add-product")
    private RedirectView addProduct(@RequestParam String title, @RequestParam String price,
                                    @RequestParam Integer categoryId, RedirectAttributes attributes) {
        if (title.isEmpty() || price.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните все поля");
            return new RedirectView("/product/add-product");
        }
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {
            int i = Integer.parseInt(price);
            Product product = new Product(title, i);
            product.setCategory(category.get());
            productService.saveProduct(product);
        }
        return new RedirectView("/product");
    }

    @GetMapping
    private String getAllProduct(Model model) {
        List<Product> products = productService.findProducts();
        List<Category> category = categoryService.findCategory();
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "getAllProduct";
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
        return "getProduct";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return new RedirectView("/product");
    }

    @GetMapping("/update/{id}")
    public String openViewUpdateProductById(@PathVariable Integer id,
                                            @ModelAttribute("error") String error, Model model) {
        Optional<Product> productById = productService.findProductById(id);
        Product product = productById.get();
        List<Category> categories = categoryService.findCategory();
        model.addAttribute("product", product);
        model.addAttribute("error", error);
        model.addAttribute("categories", categories);
        return "updateProduct";
    }

    @PostMapping("/update/{id}")
    public RedirectView updateProductById(@PathVariable Integer id, @RequestParam Integer categoryId,
                                          @ModelAttribute Product product, RedirectAttributes attributes) {
        if (product.getTitle().isEmpty()) {
            attributes.addFlashAttribute("error", "Title не может быть пустым");
            return new RedirectView("/product/update/{id}");
        }
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        if (category.isPresent()) {
            product.setCategory(category.get());
            productService.updateProductById(id, product);
        }
        return new RedirectView("/product");
    }

    @GetMapping("/create-category")
    public String addViewToCreateCategory(Model model) {
        model.addAttribute("category", new Category());
        return "createCategory";
    }

    @PostMapping("/create-category")
    public String createCategory(@RequestParam String title) {
        categoryService.addCategory(title);
        return "redirect:/product";
    }

    @GetMapping("/category")
    public String findProductByCategoryId(@RequestParam Integer categoryId, Model model) {
        List<Category> category1 = categoryService.findCategory();
        List<Product> products = productService.findProductByCategoryId(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("category", category1);
        return "getAllProduct";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);
        return "error";
    }
}
