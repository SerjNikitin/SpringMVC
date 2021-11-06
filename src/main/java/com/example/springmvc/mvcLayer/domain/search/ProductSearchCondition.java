package com.example.springmvc.mvcLayer.domain.search;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductSearchCondition {
    private Sort.Direction sortDirection=Sort.Direction.ASC;
    private String sortField="title";

    private int pageNum =1;
    private Integer pageSize=10;

//    private Page<ProductDto> page;
}
