package com.computershop.Controller;


import com.computershop.DTO.Brand.BrandRequest;
import com.computershop.DTO.Brand.BrandResponse;
import com.computershop.Service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")

@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(brandService.getById(id));
    }
    @PostMapping
    public ResponseEntity<BrandResponse> create(@RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandService.create(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@PathVariable("id") Long id, @RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandService.update(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
