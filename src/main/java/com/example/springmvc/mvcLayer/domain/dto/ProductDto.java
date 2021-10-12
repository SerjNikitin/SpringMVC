package com.example.springmvc.mvcLayer.domain.dto;

import com.example.springmvc.mvcLayer.domain.Category;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "categoryDto")
public class ProductDto implements Serializable {
    private Integer id;
    private String title;
    private Integer price;
    private Set<Integer> categoryDto;
}