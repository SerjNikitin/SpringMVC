package com.example.springmvc.mvcLayer.domain.dto;

import com.example.springmvc.mvcLayer.domain.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = "categoryDto")
public class ProductReviewDto {

    private Integer id;

    private String review;

    private int rating;

    private User user;

    private ProductDto product;

    private LocalDate dateCreate;
}
