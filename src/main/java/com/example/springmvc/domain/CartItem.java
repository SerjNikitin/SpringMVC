package com.example.springmvc.domain;

import com.example.springmvc.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
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
