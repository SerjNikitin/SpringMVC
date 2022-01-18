package com.example.springmvc.mvcLayer.component;

import com.example.springmvc.mvcLayer.domain.cart.CartItem;
import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import com.example.springmvc.mvcLayer.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@NoArgsConstructor
public class ShoppingCart {

    private final Map<Integer, CartItem> cartItems = new HashMap<>();
    private Integer totalPrice = 0;

    public void addCartItem(ProductDto productDto) {
        Integer id = productDto.getId();
        if (cartItems.containsKey(id)) {
            cartItems.get(id).increaseCount();
        } else {
            cartItems.put(id, new CartItem(productDto));
        }
        updateTotalPrice();
    }

    public Boolean deleteCartItem(Integer productId) {
        if (cartItems.containsKey(productId)) {
            if (cartItems.get(productId).getCount() == 1) {
                cartItems.remove(productId);
            } else {
                cartItems.get(productId).reduceCount();
            }
            updateTotalPrice();
            return true;
        } else return false;
    }

    public void addSameItem(Integer productId) {
        if (cartItems.containsKey(productId)) {
            cartItems.get(productId).increaseCount();
            updateTotalPrice();
        }
    }

    public void deleteSameItem(Integer productId) {
        if (cartItems.containsKey(productId)) {
            if (cartItems.get(productId).getCount() == 1) {
                deleteCartItem(productId);
                return;
            }
            cartItems.get(productId).reduceCount();
        }
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = 0;
        for (CartItem cartItem : cartItems.values()) {
            totalPrice += (cartItem.getProduct().getPrice() * cartItem.getCount());
        }
    }

    public int getCount() {
        int count = 0;
        for (CartItem cartItem : cartItems.values()) {
            count += cartItem.getCount();
        }
        return count;
    }

    public Map<Integer, CartItem> getCartItems() {
        return new HashMap<>(cartItems);
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}