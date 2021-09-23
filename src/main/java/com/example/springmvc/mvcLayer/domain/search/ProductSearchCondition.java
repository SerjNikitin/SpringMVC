package com.example.springmvc.mvcLayer.domain.search;

import com.example.springmvc.mvcLayer.domain.dto.ProductDto;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Data
public class ProductSearchCondition {
    private Sort.Direction sortDirection=Sort.Direction.ASC;
    private String sortField="title";

    private int pageNum =1;
    private Integer pageSize=10;

//    private Page<ProductDto> page;
}
