package com.example.springmvc.mvcLayer.component;

import com.example.springmvc.mvcLayer.domain.cart.CartItem;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@NoArgsConstructor
public class ShoppingCart {

    private final Map<Integer, CartItem> cartItems = new HashMap<>();
    private Integer totalPrice = 0;

    public void addCartItem(CartItem cartItem) {
        Integer productId = cartItem.getProduct().getId();
        if (cartItems.containsKey(productId)) {
            cartItems.get(productId).increaseCount();
        }
        cartItems.put(productId, cartItem);
        updateTotalPrice();
    }

    public void deleteCartItem(Integer productId) {
        cartItems.remove(productId);
        updateTotalPrice();
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