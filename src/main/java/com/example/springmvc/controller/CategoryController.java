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

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

    @GetMapping("/create-category")
    public String addViewToCreateCategory(Model model,@ModelAttribute("error") String error) {
        model.addAttribute("category", new Category());
        return "category/createCategory";
    }

    @PostMapping("/create-category")
    public RedirectView createCategory(@RequestParam String title, RedirectAttributes attributes) {
        if (title.isEmpty()){
            attributes.addFlashAttribute("error","Заполните поле название категории");
            return new RedirectView("/create-category");
        }
        categoryService.addCategory(title);
        return new RedirectView ("/product");
    }

    @GetMapping("/category")
    public String findProductByCategoryId(@RequestParam Integer categoryId, Model model) {
        List<Category> category = categoryService.findCategory();
        List<Product> products = productService.findProductByCategoryId(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        return "product/getAllProduct";
    }
}
