package com.computershop.Controller;

import com.computershop.DTO.Product.ProductRequest;
import com.computershop.DTO.Product.ProductResponse;
import com.computershop.Service.ProductService;
import com.computershop.Util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> create(@RequestBody @Valid ProductRequest payload) {
        return ApiResponse.create(productService.create(payload));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductResponse> update(@PathVariable("id") Long id, @RequestBody @Valid ProductRequest payload) {
        return ApiResponse.ok(productService.update(id,payload));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProductResponse>> list() {
        return ApiResponse.ok(productService.list());
    }
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProductResponse>> filter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId
    ) {

        return ApiResponse.ok(productService.filter(id, name, status, brandId, categoryId));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductResponse> getById(@PathVariable("id") Long id) {
        return ApiResponse.ok(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
