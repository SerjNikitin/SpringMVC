package com.example.springmvc.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    //    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + title + ", " + price + "}";
    }
}
