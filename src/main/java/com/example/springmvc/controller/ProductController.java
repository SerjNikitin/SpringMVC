package com.example.springmvc.controller;


import com.example.springmvc.domain.Category;
import com.example.springmvc.domain.Product;
import com.example.springmvc.domain.search.ProductSearchCondition;
import com.example.springmvc.domain.dto.ProductDto;
import com.example.springmvc.service.CategoryService;
import com.example.springmvc.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.springmvc.domain.constans.ConstanceName.*;

@AllArgsConstructor
@Controller
@RequestMapping(PRODUCT)
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;


    @GetMapping(LIST)
    public String getListProducts(ProductSearchCondition searchCondition,Model model) {
//        List<Product> products = productService.findProducts();
        Page<Product> page = productService.findAllBySearchConditional(searchCondition);


        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("pageNum", searchCondition.getPageNum());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", searchCondition.getPageSize());
//        model.addAttribute("page", page);
        return "product/list";
    }

    @GetMapping(FORM)
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

    @PostMapping(FORM)
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

    @GetMapping(DELETE)
    public String deleteProductById(@RequestParam Integer id, Model model) {
        productService.deleteProductById(id);
        model.addAttribute("products", productService.findProducts());
        return "product/list";
    }

    @GetMapping(FILTER)
    public String filterProductsByTitleAndByMaxAndMinPrice(@RequestParam(required = false) String title,
                                                           @RequestParam(required = false) Integer minPrice,
                                                           @RequestParam(required = false) Integer maxPrice, Model model) {
        List<Product> products = productService.findProductsByTitleAndByMaxAndMinPrice(title, minPrice, maxPrice);
        model.addAttribute("products", products);
        return ("product/list");
    }
}