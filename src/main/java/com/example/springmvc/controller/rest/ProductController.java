package com.example.springmvc.controller.rest;

import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.search.ProductSearchCondition;
import com.example.springmvc.domain.dto.ProductDto;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.springmvc.domain.constans.ConstanceName.*;

@AllArgsConstructor
@RequestMapping(API_V1+PRODUCT)
@RestController("restProductController")
public class ProductController {

    private ProductService productService;

    @GetMapping("/{id}")
    private ProductDto getProductById(@PathVariable Integer id) {
        return productService.findProductDtoById(id);
    }
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.findProducts();
//    }

    @PostMapping
    public Page<Product> getAllProducts(@RequestBody ProductSearchCondition searchCondition) {
        return productService.findAllBySearchConditional(searchCondition);
    }


    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return HttpStatus.OK.value();
    }
}
