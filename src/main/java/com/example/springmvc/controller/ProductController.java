package com.example.springmvc.controller;


import com.example.springmvc.domain.Product;
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

    @GetMapping("/add-product")
    public String addFormToAddProduct(Model model, @ModelAttribute("error") String error) {
        model.addAttribute("product", new Product());
        model.addAttribute("error", error);
        return "addProduct";
    }

    @PostMapping("/add-product")
    private RedirectView addProduct(@ModelAttribute Product product, RedirectAttributes attributes) {
        if (product.getTitle().isEmpty()) {
            attributes.addFlashAttribute("error", "Title не может быть пустым");
            return new RedirectView("/product/add-product");
        }
        productService.add(product);
        return new RedirectView("/product");
    }

    @GetMapping
    private String getAllProduct(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/findById")
    public String filterById(@RequestParam Integer id, Model model) {
//        Optional<Product> productById = productService.findProductId(Integer.parseInt(id));
        Optional<Product> productById = productService.findProductId(id);

        if (productById.isPresent()) {
            model.addAttribute("products", Collections.singletonList(productById.get()));
        } else {
            model.addAttribute("products", Collections.emptyList());
        }
        return "getProduct";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);

        return "error";
    }
}
