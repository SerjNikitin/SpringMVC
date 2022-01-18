package com.example.springmvc.mvcLayer.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
//@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "categoryDto")
public class ProductDto implements Serializable {
    private Integer id;
    private String title;
    private Integer price;
    private Set<Integer> categoryDto;
    private Integer countProduct;
//    @Override
//    public String toString() {
//        return "id=" + id +
//                ", title='" + title + '\'' +
//                ", price=" + price +".";
//    }
}