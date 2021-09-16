package com.example.springmvc.mvcLayer.domain.productMarket;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Builder
@ToString(exclude = {"categories"})
@EqualsAndHashCode(exclude = {"id", "categories"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
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
//    @Override
//    public String toString() {
//        return "{" + id + ", " + title + ", " + price + "," + category + "," + image + "}";
//    }
}
