package com.example.springmvc.mvcLayer.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@ToString(exclude = {"categories"})
@EqualsAndHashCode(exclude = {"id", "categories"})
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

    @Column(name = "image")
    private String image;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
