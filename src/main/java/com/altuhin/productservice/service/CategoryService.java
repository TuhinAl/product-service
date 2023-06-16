package com.altuhin.productservice.service;

import com.altuhin.productservice.controller.request_dto.CategorySearchDto;
import com.altuhin.productservice.dto.CategoryDto;
import com.altuhin.productservice.entity.Category;
import com.altuhin.productservice.entity.QProduct;
import com.altuhin.productservice.repository.CategoryRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static com.altuhin.productservice.service.predicate.CategoryPredicate.searchPredicate;
import static com.altuhin.productservice.service.predicate.ProductPredicate.searchPredicate;
import static com.altuhin.productservice.util.TransformUtil.copyList;
import static com.altuhin.productservice.util.TransformUtil.copyProp;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    
    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryRepository.save(copyProp(categoryDto, Category.class));
        return copyProp(categoryRepository.save(category), CategoryDto.class);
    }
    
    @Transactional
    public CategoryDto update(CategoryDto productDto) {
        
        Category category = categoryRepository.findById(productDto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Category With This Id is not Found!")
                );
       
        return copyProp(categoryRepository.save(category), CategoryDto.class);
    }
    
    public Page<CategoryDto> search(CategorySearchDto categorySearchDto) {
        final QProduct qProduct = QProduct.product;
        final JPAQuery<Category> categoryQuery = new JPAQuery<>(entityManager);
        
        Predicate predicate = searchPredicate(categorySearchDto);
        Pageable pageable = PageRequest.of(categorySearchDto.getPage(), categorySearchDto.getSize());
        
        var query = categoryQuery
                .from(qProduct)
                .where(predicate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qProduct.createdDate.desc());
        
        return new PageImpl<>( copyList(query.fetch(), CategoryDto.class), pageable, query.fetchCount());
    }
}
