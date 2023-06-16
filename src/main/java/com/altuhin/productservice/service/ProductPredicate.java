package com.altuhin.productservice.service;

import com.altuhin.productservice.controller.request_dto.ProductSearchDto;
import com.altuhin.productservice.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;

public class ProductPredicate {
    private static final QProduct qProduct = QProduct.product;
    
    public static BooleanBuilder searchPredicate(ProductSearchDto productSearchDto) {
        
        BooleanBuilder builder = new BooleanBuilder();
        
        if (StringUtils.isNotBlank(productSearchDto.getMultiSearchProperty())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qProduct.id.containsIgnoreCase(productSearchDto.getMultiSearchProperty()))
                    .or(qProduct.productName.containsIgnoreCase(productSearchDto.getMultiSearchProperty()))
                    .or(qProduct.description.containsIgnoreCase(productSearchDto.getMultiSearchProperty()));
            builder.and(booleanBuilder);
        }
        
        
        if (StringUtils.isNotBlank(productSearchDto.getProductName())) {
            builder.and(qProduct.productName.eq(productSearchDto.getProductName()));
        }
        
        return builder;
    }
}
