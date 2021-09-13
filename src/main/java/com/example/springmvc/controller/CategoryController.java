package com.example.springmvc.controller;

import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static com.example.springmvc.domain.constans.ConstanceName.*;

@AllArgsConstructor
@Controller
@RequestMapping(CATEGORY)
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping(FORM)
    public String addViewToCreateCategory(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping(FORM)
    public RedirectView createCategory(@RequestParam String title, RedirectAttributes attributes) {
        if (title.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле название категории");
            return new RedirectView("/form");
        }
        categoryService.addCategory(title);
        return new RedirectView("/category/list");
    }

    @GetMapping(LIST)
    public String findAllCategory(Model model) {
        List<Category> categories = categoryService.findCategory();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping(FIND)
    public String findProductByCategoryId(@RequestParam Integer categoryId, Model model) {
        List<Product> products = categoryService.findProductsByCategoryId(categoryId);
        List<Category> category = categoryService.findCategory();
        model.addAttribute("category", category);
        model.addAttribute("products", products);

        return "product/list";
    }
}
