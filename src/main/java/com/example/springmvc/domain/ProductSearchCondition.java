package com.example.springmvc.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductSearchCondition {
    private Sort.Direction sortDirection=Sort.Direction.ASC;
    private String sortField="title";

    private int pageNum;
    private Integer pageSize=20;
}
