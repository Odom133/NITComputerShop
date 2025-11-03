package com.computershop.Controller;

import com.computershop.DTO.Category.CategoryEditRequest;
import com.computershop.DTO.Category.CategoryRequest;
import com.computershop.DTO.Category.CategoryResponse;
import com.computershop.Model.Entity.Category;
import com.computershop.Service.CategoryService;
import com.computershop.Util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

//    @GetMapping("/filter")
//    @ResponseStatus(HttpStatus.OK)
//    public ApiResponse<?> filter(@RequestParam(required = false) Long id, @RequestParam( required = false) String name) {
//        Map<String, Object> res = this.categoryService.filter(id,name);
//        List<Category> categories = (List<Category>) res.get("data");
//        return ApiResponse.ok(categoryService.filter(id,name));
//    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> filter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, // 2025-10-18T00:00:00
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, // 2025-10-18T23:59:59
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortAs
            ) {
        List<Category> categories = categoryService.filter(id,name,code,startDate,endDate,sortBy,sortAs);
        return ApiResponse.ok(categories);
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
