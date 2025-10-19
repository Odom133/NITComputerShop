package com.computershop.Controller;

import com.computershop.DTO.Category.CategoryEditRequest;
import com.computershop.DTO.Category.CategoryRequest;
import com.computershop.DTO.Category.CategoryResponse;
import com.computershop.Service.CategoryService;
import com.computershop.Util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(ApiResponse.ok(this.categoryService.getAll()));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .body(ApiResponse.ok(this.categoryService.getById(id)));
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<?> getById(@PathVariable("id") Long id) {
        return ApiResponse.ok(categoryService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> create(@Valid  @RequestBody CategoryRequest request) {
        return ApiResponse.create(categoryService.create(request));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> update(@PathVariable("id") Long id, @Valid @RequestBody CategoryEditRequest request ) {
        return ApiResponse.ok(categoryService.update(id,request));
    }
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//        this.categoryService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(ApiResponse.ok(null));
//    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
    }

}
