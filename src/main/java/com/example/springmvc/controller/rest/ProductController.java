package com.example.springmvc.controller.rest;

import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.ProductSearchCondition;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/product")
@RestController("restProductController")
public class ProductController {

    private ProductService productService;

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable Integer id) {
        return productService.findProductById(id).get();
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestBody ProductSearchCondition searchCondition) {
//        return productService.pagination(searchCondition);
        return productService.findProducts();
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return HttpStatus.OK.value();
    }
}
