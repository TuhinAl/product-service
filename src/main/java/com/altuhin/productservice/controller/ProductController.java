package com.altuhin.productservice.controller;


import com.altuhin.productservice.controller.request_dto.ProductSearchDto;
import com.altuhin.productservice.dto.ProductDto;
import com.altuhin.productservice.service.ProductService;
import com.altuhin.productservice.util.ApiResponse;
import com.altuhin.productservice.util.ApiResponseEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    private final ApiResponseEntityFactory responseFactory;
    
    @PostMapping()
    public ResponseEntity<ApiResponse<ProductDto>> save(@RequestBody ProductDto productDto) {
        return responseFactory.saveResponse(productService.save(productDto));
    }
    
    @PutMapping()
    public ResponseEntity<ApiResponse<ProductDto>> update(@RequestBody ProductDto productDto) {
        return responseFactory.updateResponse("Successfully Updated",
                productService.update(productDto));
    }
    
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<Page<ProductDto>>> search(@RequestBody ProductSearchDto
                                                                        productSearchDto) {
        return responseFactory.getResponse(productService.search(productSearchDto));
        
    }
}
