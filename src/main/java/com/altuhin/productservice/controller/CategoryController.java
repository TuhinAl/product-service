package com.altuhin.productservice.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        return categoryService.updateCategory(categoryDto, id);
    }

    @GetMapping("/get/by/{id}")
    public CategoryDto getCategory(@PathVariable Integer id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/{id}")
    public CategoryDto deleteCategory(@PathVariable Integer id) {
        return categoryService.deleteCategory(id);
    }

}
