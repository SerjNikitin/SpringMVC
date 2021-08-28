package com.example.springmvc.controller;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

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
}
