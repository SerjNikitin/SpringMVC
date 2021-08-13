package com.example.springmvc.controller;


import com.example.springmvc.domain.Product;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("/add-product")
    private String addForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @PostMapping("/add-product")
    private String addProduct(@ModelAttribute Product product) {
        productService.add(product);
        return "redirect:/product";
    }

    @GetMapping
    private String getAllProduct(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    @ResponseBody
    private Product getProductById(@PathVariable(value = "id") Integer id) {
        return productService.findProductId(id).get();
    }
}
