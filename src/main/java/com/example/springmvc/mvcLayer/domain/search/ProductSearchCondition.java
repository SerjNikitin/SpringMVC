package com.example.springmvc.mvcLayer.domain.search;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductSearchCondition {
    private Sort.Direction sortDirection=Sort.Direction.ASC;
    private String sortField="title";

    private int pageNum;
    private Integer pageSize=20;

//    private List<Integer> pageNumbers = Collections.singletonList(1);
//
//    private Page<ProductDto> page;

//    private String titleFilter;
}
