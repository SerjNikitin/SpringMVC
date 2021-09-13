package com.example.springmvc.controller;

import com.example.springmvc.component.ShoppingCart;
import com.example.springmvc.domain.CartItem;
import com.example.springmvc.domain.dto.ProductDto;
import com.example.springmvc.service.ProductService;
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
        shoppingCart.addCartItem(new CartItem(productDto));
        return new RedirectView("/product/list");
    }

    @GetMapping("/delete-from-cart")
    public RedirectView deleteProductFromCart(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.deleteCartItem(id);
        return new RedirectView("/product/list");
    }

    @GetMapping("/plus-one")
    public RedirectView addSameItem(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.addSameItem(id);
        return new RedirectView("/cart");
    }

    @GetMapping("/minus-one")
    public RedirectView deleteSameItem(Integer id, @ModelAttribute ShoppingCart shoppingCart) {
        shoppingCart.deleteSameItem(id);
        return new RedirectView("/cart");
    }
}