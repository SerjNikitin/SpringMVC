package com.example.springmvc.mvcLayer.domain;

import com.example.springmvc.mvcLayer.domain.security.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_review")
@ToString(exclude = {"user","product"})
@EqualsAndHashCode(exclude = {"id", "user","product"})
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "review")
    private String review;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date_create")
    private LocalDate dateCreate;
}
