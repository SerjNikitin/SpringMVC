package com.example.springmvc.mvcLayer.domain.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import org.junit.jupiter.api.Test;

class CartItemTest {
    @Test
    void testConstructor() {
        ProductDto productDto = new ProductDto();
        CartItem actualCartItem = new CartItem(productDto);
        assertEquals(1, actualCartItem.getCount().intValue());
        assertSame(productDto, actualCartItem.getProduct());
    }

    @Test
    void testIncreaseCount() {
        CartItem cartItem = new CartItem(new ProductDto());
        cartItem.increaseCount();
        assertEquals(2, cartItem.getCount().intValue());
    }

    @Test
    void testReduceCount() {
        CartItem cartItem = new CartItem(new ProductDto());
        cartItem.reduceCount();
        assertEquals(0, cartItem.getCount().intValue());
    }
}

