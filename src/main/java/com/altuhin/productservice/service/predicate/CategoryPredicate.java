package com.altuhin.productservice.service.predicate;

import com.altuhin.productservice.controller.request_dto.CategorySearchDto;
import com.altuhin.productservice.controller.request_dto.ProductSearchDto;
import com.altuhin.productservice.entity.QCategory;
import com.altuhin.productservice.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;

public class CategoryPredicate {
    private static final QCategory qCategory = QCategory.category;
    
    public static BooleanBuilder searchPredicate(CategorySearchDto categorySearchDto) {
        
        BooleanBuilder builder = new BooleanBuilder();
        
        if (StringUtils.isNotBlank(categorySearchDto.getMultiSearchProperty())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qCategory.id.containsIgnoreCase(categorySearchDto.getMultiSearchProperty()))
                    .or(qCategory.categoryName.containsIgnoreCase(categorySearchDto.getMultiSearchProperty()))
                    .or(qCategory.description.containsIgnoreCase(categorySearchDto.getMultiSearchProperty()));
            builder.and(booleanBuilder);
        }
        
        
        if (StringUtils.isNotBlank(categorySearchDto.getCategoryName())) {
            builder.and(qCategory.categoryName.eq(categorySearchDto.getCategoryName()));
        }
        
        return builder;
    }
}
