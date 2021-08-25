package com.example.springmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category", schema = "market")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "category",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Category(String category) {
        this.title=category;
    }

    @Override
    public String toString() {
        return "title= " + title ;
    }
}