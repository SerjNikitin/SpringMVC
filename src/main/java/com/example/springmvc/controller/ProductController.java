package com.example.springmvc.controller;


import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.dto.ProductDto;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;


    @GetMapping("/list")
    public String getListProducts(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/form")
    public String getProductForm(Model model, @RequestParam(required = false) Integer id) {
        List<Category> categories = categoryService.findCategory();
        model.addAttribute("categories", categories);
        if (id != null) {
            ProductDto productDtoById = productService.findProductDtoById(id);
            model.addAttribute("product", productDtoById);
        } else {
            model.addAttribute("product", new ProductDto());
        }
        return "product/form";
    }

    @PostMapping("/form")
    public RedirectView saveProduct(ProductDto product,
                                    @RequestParam(required = false) MultipartFile image) {
        productService.saveProductAndImage(product, image);
        return new RedirectView("/product/list");
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);
        return "error";
    }

    @GetMapping("/delete")
    public String deleteProductById(@RequestParam Integer id, Model model) {
        productService.deleteProductById(id);
        model.addAttribute("products", productService.findProducts());
        return "product/list";
    }

    @GetMapping("/filter")
    public String filterProductsByTitleAndByMaxAndMinPrice(@RequestParam(required = false) String title,
                                                           @RequestParam(required = false) Integer minPrice,
                                                           @RequestParam(required = false) Integer maxPrice, Model model) {
        List<Product> products = productService.findProductsByTitleAndByMaxAndMinPrice(title, minPrice, maxPrice);
        model.addAttribute("products", products);
        return ("product/list");
    }
}