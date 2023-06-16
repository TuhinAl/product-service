package com.altuhin.productservice.service;


import com.altuhin.productservice.controller.request_dto.ProductSearchDto;
import com.altuhin.productservice.dto.ProductDto;
import com.altuhin.productservice.entity.Category;
import com.altuhin.productservice.entity.Product;
import com.altuhin.productservice.entity.QProduct;
import com.altuhin.productservice.repository.CategoryRepository;
import com.altuhin.productservice.repository.ProductRepository;
import com.altuhin.productservice.service.predicate.ProductPredicate;
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

import static com.altuhin.productservice.util.TransformUtil.copyList;
import static com.altuhin.productservice.util.TransformUtil.copyProp;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    
    @Transactional
    public ProductDto save(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category With This Id is not Found!"));
        Product product = productRepository.save(copyProp(productDto, Product.class));
        product.setCategory(category);
        return copyProp(productRepository.save(product), ProductDto.class);
    }
    
    @Transactional
    public ProductDto update(ProductDto productDto) {
        
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category With This Id is not Found!"));
        Product product = productRepository.save(copyProp(productDto, Product.class));
        product.setCategory(category);
        return copyProp(productRepository.save(product), ProductDto.class);
    }
    
    public Page<ProductDto> search(ProductSearchDto productSearchDto) {
        final QProduct qProduct = QProduct.product;
        final JPAQuery<Product> productQuery = new JPAQuery<>(entityManager);
        
        Predicate predicate = ProductPredicate.searchPredicate(productSearchDto);
        Pageable pageable = PageRequest.of(productSearchDto.getPage(), productSearchDto.getSize());
        
        var query = productQuery.from(qProduct)
                .where(predicate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(qProduct.createdDate.desc());
        
        return new PageImpl<>(copyList(query.fetch(), ProductDto.class), pageable, query.fetchCount());
    }
}
