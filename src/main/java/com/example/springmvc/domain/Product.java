package com.example.springmvc.domain;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product(Integer id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + title + ", " + price + category+"}";
    }
}
