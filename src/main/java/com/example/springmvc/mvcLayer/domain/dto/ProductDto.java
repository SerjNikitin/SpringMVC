package com.example.springmvc.mvcLayer.domain.dto;

import com.example.springmvc.mvcLayer.domain.productMarket.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String title;
    private Integer price;
    private Set<Category> categories;
}