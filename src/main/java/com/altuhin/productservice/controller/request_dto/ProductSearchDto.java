package com.altuhin.productservice.controller.request_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductSearchDto extends SearchDto{
    private String id;
    private String productName;
    private String description;
    private BigDecimal unitPrice;
    private Integer unitsInStock;
    private String categoryId;
    private String categoryName;
}
