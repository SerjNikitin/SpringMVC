package com.example.springmvc.mvcLayer.controller;

import com.example.springmvc.mvcLayer.component.ShoppingCart;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("shoppingCart")
@AllArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ProductService productService;

    @GetMapping
    public String getCartList(@ModelAttribute ShoppingCart shoppingCart) {
        return "cart/list";
    }

    @GetMapping("/add-to-cart")
    public RedirectView addProductToCart(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        ProductDto productDto = productService.findProductDtoById(id);
        if (productDto.getCountProduct() > 0) {
            shoppingCart.addCartItem(productDto);
            productService.updateCountInProduct(productDto.getId(), (productDto.getCountProduct() - 1));
        }
        return new RedirectView("/product/list");
    }

    @GetMapping("/delete-from-cart")
    public RedirectView deleteProductFromCart(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        Boolean isPresents = shoppingCart.deleteCartItem(id);
        if (isPresents){
            ProductDto productDto = productService.findProductDtoById(id);
            productService.updateCountInProduct(id, (productDto.getCountProduct() + 1));
        }
        return new RedirectView("/product/list");
    }

    @GetMapping("/plus-one")
    public RedirectView addSameItem(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        ProductDto productDto = productService.findProductDtoById(id);
        if (productDto.getCountProduct() > 0) {
            shoppingCart.addSameItem(id);
            productService.updateCountInProduct(productDto.getId(), (productDto.getCountProduct() - 1));
        }
        return new RedirectView("/cart");
    }

    @GetMapping("/minus-one")
    public RedirectView deleteSameItem(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.deleteSameItem(id);
        ProductDto productDto = productService.findProductDtoById(id);
        productService.updateCountInProduct(id, (productDto.getCountProduct() + 1));
        return new RedirectView("/cart");
    }
}