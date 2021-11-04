package com.example.springmvc.mvcLayer.controller;

import com.example.springmvc.mvcLayer.domain.Product;
import com.example.springmvc.mvcLayer.domain.ProductReview;
import com.example.springmvc.mvcLayer.service.ProductService;
import com.example.springmvc.mvcLayer.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.springmvc.mvcLayer.domain.constans.ConstanceName.*;

@Controller
@AllArgsConstructor
@RequestMapping(REVIEW)
public class ProductReviewController {

    private final ReviewService reviewService;
    private final ProductService productService;

    @GetMapping
    public String getReviewByProduct(Model model, Integer id) {
        List<ProductReview> reviewList = reviewService.findReviewByProductId(id);
        model.addAttribute("reviewList", reviewList);
        return "review/list";
    }

    @GetMapping(FORM)
    public String createViewForReviewProduct(Model model, Integer id) {
        Optional<Product> productById = productService.findProductById(id);

        ProductReview productReview = new ProductReview();
        productReview.setProduct(productById.get());
        model.addAttribute("productReview", productReview);
        return "/review/form";
    }

    @PostMapping(FORM)
    public String createReviewProduct(@ModelAttribute ProductReview productReview, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        reviewService.saveReview(productReview, name);
        List<ProductReview> reviewList = reviewService.findReviewByProductId(productReview.getProduct().getId());
        model.addAttribute("reviewList", reviewList);
        return "review/list";
    }

    @GetMapping(DELETE)
    public String deleteReview(Integer id) {
        reviewService.deleteReview(id);
        return "review/list";
    }
}
