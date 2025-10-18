package com.computershop.Service;

import com.computershop.DTO.Category.CategoryRequest;
import com.computershop.DTO.Category.CategoryResponse;
import com.computershop.Mapper.CategoryMapper;
import com.computershop.Model.Entity.Category;
import com.computershop.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();

//                .collect(Collectors.toList());
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found")  );
        return categoryMapper.toResponse(category);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByCode(categoryRequest.getCode())){
            throw new RuntimeException("Category code exists.");
        }
        Category category = categoryMapper.toEntity(categoryRequest);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }
}
