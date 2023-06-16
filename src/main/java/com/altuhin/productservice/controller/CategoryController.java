package com.altuhin.productservice.controller;
import com.altuhin.productservice.controller.request_dto.CategorySearchDto;
import com.altuhin.productservice.controller.request_dto.ProductSearchDto;
import com.altuhin.productservice.dto.CategoryDto;
import com.altuhin.productservice.dto.ProductDto;
import com.altuhin.productservice.service.CategoryService;
import com.altuhin.productservice.util.ApiResponse;
import com.altuhin.productservice.util.ApiResponseEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    private final ApiResponseEntityFactory responseFactory;
    
    
    @PostMapping()
    public ResponseEntity<ApiResponse<CategoryDto>> save(@RequestBody CategoryDto categoryDto) {
        return responseFactory.saveResponse(categoryService.save(categoryDto));
    }
    
    @PutMapping()
    public ResponseEntity<ApiResponse<CategoryDto>> update(@RequestBody CategoryDto categoryDto) {
        return responseFactory.updateResponse("Successfully Updated",
                categoryService.update(categoryDto));
    }
    
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> search(@RequestBody CategorySearchDto
                                                                        categorySearchDto) {
        return responseFactory.getResponse(categoryService.search(categorySearchDto));
        
    }

}
