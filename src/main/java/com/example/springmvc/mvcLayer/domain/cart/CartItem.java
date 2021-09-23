package com.example.springmvc.mvcLayer.domain.cart;

import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public class CartItem {
    private final ProductDto product;
    private Integer count;

    public CartItem(ProductDto product) {
        this.product = product;
        this.count = 1;
    }

    public void increaseCount() {
        this.count++;
    }

    public void reduceCount() {
        if (count > 0) {
            count--;
        }
    }
}
