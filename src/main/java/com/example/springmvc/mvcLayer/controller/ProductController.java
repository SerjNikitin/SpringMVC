package com.example.springmvc.mvcLayer.controller;


import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import com.example.springmvc.mvcLayer.domain.productMarket.Product;
import com.example.springmvc.mvcLayer.domain.search.ProductSearchCondition;
import com.example.springmvc.mvcLayer.service.CategoryService;
import com.example.springmvc.mvcLayer.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.springmvc.mvcLayer.domain.constans.ConstanceName.*;

@AllArgsConstructor
@Controller
@RequestMapping(PRODUCT)
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;


    @GetMapping(LIST)
    public String getListProducts(@ModelAttribute ProductSearchCondition searchCondition, Model model) {
        Page<Product> page = productService.findAllBySearchConditional(searchCondition);
        productService.pagination(searchCondition, model, page);
        return "product/list";
    }

    @GetMapping(FORM)
    public String getProductForm(Model model, @RequestParam(required = false) Integer id,
                                 @ModelAttribute("error") String error) {
        List<Category> categories = categoryService.findCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("error", error);
        if (id != null) {
            ProductDto productDtoById = productService.findProductDtoById(id);
            model.addAttribute("product", productDtoById);
        } else {
            model.addAttribute("product", new ProductDto());
        }
        return "product/form";
    }

    @PostMapping(FORM)
    public RedirectView saveProduct(@ModelAttribute ProductDto product, RedirectAttributes attributes,
                                    @RequestParam(required = false) MultipartFile image) {
        boolean redirectView = getRedirectView(product.getTitle(), product.getPrice(), attributes, image);
        if (redirectView) {
            return new RedirectView(PRODUCT+FORM);
        }
        productService.saveProductAndImage(product, image);
        return new RedirectView(PRODUCT+LIST);
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception ex) {
        System.err.println("Request: " + req.getRequestURL() + " raised " + ex);
        return "error";
    }

    @GetMapping(DELETE)
    public RedirectView deleteProductById(@RequestParam Integer id) {
                productService.deleteProductById(id);
        return new RedirectView(PRODUCT+LIST);
    }

    @GetMapping(FILTER)
    public String filterProductsByTitleAndByMaxAndMinPrice(@ModelAttribute ProductSearchCondition searchCondition,
                                                           @RequestParam(required = false) String title,
                                                           @RequestParam(required = false) Integer minPrice,
                                                           @RequestParam(required = false) Integer maxPrice, Model model) {
        Page<Product> page = productService.findProductsByTitleAndByMaxAndMinPriceBySearchConditional(
                searchCondition, title, minPrice, maxPrice);
        productService.pagination(searchCondition, model, page);
        return ("product/list");
    }

    private boolean getRedirectView(String title, Integer price, RedirectAttributes attributes, MultipartFile image) {
        if (title.isEmpty()) {
            attributes.addFlashAttribute("error", "Заполните поле с названием продукта");
            return true;
        }
        if (price==null) {
            attributes.addFlashAttribute("error", "Заполните поле с ценой продукта");
            return true;
        }
        return false;
    }
}