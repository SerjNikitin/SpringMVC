package com.example.springmvc.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product", schema = "market")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Название обязательно")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Цена обязательна")
    @Column(name = "price")
    private Integer price;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image")
    private String image;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }
//
//    public Product(Integer id, String title, Integer price) {
//        this.id = id;
//        this.title = title;
//        this.price = price;
//    }

    @Override
    public String toString() {
        return "{" + id + ", " + title + ", " + price +"," + category+","+image+"}";
    }
}
