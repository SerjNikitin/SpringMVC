package com.example.springmvc.controller.rest;


import com.example.springmvc.domain.Category;
import com.example.springmvc.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.springmvc.domain.constans.ConstanceName.*;
@AllArgsConstructor
@RequestMapping(API_V1+CATEGORY)
@RestController("restCategoryController")
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.findCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.findCategoryById(id).get();
    }

    @PostMapping
    public int addCategory(@RequestBody String title) {
        categoryService.addCategory(title);
        return HttpStatus.OK.value();
    }

    @PutMapping("/{id}")
    public int updateCategory(@PathVariable Integer id, @RequestBody String title) {
        categoryService.updateCategory(id, title);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/{id}")
    public int deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return HttpStatus.OK.value();
    }
}