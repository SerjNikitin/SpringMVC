package com.example.springmvc.mvcLayer.controller;


import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import com.example.springmvc.mvcLayer.domain.productMarket.Product;
import com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.service.CategoryService;
import com.example.springmvc.mvcLayer.service.ProductService;
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

import static com.example.springmvc.mvcLayer.domain.constans.ConstanceName.*;

@AllArgsConstructor
@Controller
@RequestMapping(PRODUCT)
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;


    @GetMapping(LIST)
    public String getListProducts(ProductSearchCondition searchCondition,Model model) {
        Page<Product> page = productService.findAllBySearchConditional(searchCondition);
        pagination(searchCondition, model, page);
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
    public String deleteProductById(@RequestParam Integer id, ProductSearchCondition searchCondition, Model model) {
        Page<Product> page = productService.deleteProductById(id, searchCondition);
        pagination(searchCondition,model, page);
        return "product/list";
    }

    @GetMapping(FILTER)
    public String filterProductsByTitleAndByMaxAndMinPrice(ProductSearchCondition searchCondition,
                                                           @RequestParam(required = false) String title,
                                                           @RequestParam(required = false) Integer minPrice,
                                                           @RequestParam(required = false) Integer maxPrice, Model model) {
        Page<Product> page = productService.findProductsByTitleAndByMaxAndMinPriceBySearchConditional(
                searchCondition, title, minPrice, maxPrice);
        pagination(searchCondition, model, page);

        return ("product/list");
    }

    private void pagination(ProductSearchCondition searchCondition, Model model, Page<Product> page) {
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
    }
}