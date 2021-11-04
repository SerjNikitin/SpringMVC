package com.example.springmvc.mvcLayer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCartDto {

    private Integer id;
    private String title;
    private Integer price;

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", price=" + price +".";
    }
}
