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
        productService.saveProduct(product);
        return new RedirectView("/product");
    }

    @GetMapping
    private String getAllProduct(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "getAllProduct";
    }

    @GetMapping("/findById")
    public String filterById(@RequestParam Integer id, Model model) {
        Optional<Product> productById = productService.findProductById(id);
        if (productById.isPresent()) {
            Product product = productById.get();
            model.addAttribute("product", product);
//            model.addAttribute("products", Collections.singletonList(productById.get()));
//        } else {
//            model.addAttribute("product", Collections.emptyList());
        }
        return "getProduct";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);
        return "error";
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
        model.addAttribute("product", product);
        model.addAttribute("error", error);
        return "updateProduct";
    }

    @PostMapping("/update/{id}")
    public RedirectView updateProductById(@PathVariable Integer id,
                                          @ModelAttribute Product product, RedirectAttributes attributes) {
        if (product.getTitle().isEmpty()) {
            attributes.addFlashAttribute("error", "Title не может быть пустым");
            return new RedirectView("/product/update/{id}");
        }
        productService.updateProductById(id, product.getTitle(), product.getPrice());
        return new RedirectView("/product");

    }
}
