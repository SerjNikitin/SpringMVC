package com.example.springmvc.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String title;
    private double price;

    @Override
    public String toString() {
        return "{"+id+", "+ title+", "+price+"}";
    }
}
