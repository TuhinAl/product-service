package com.altuhin.productservice.repository;

import com.altuhin.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>,
        QuerydslPredicateExecutor<Category> {
}