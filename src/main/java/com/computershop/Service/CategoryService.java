package com.computershop.Service;

import com.computershop.DTO.Category.CategoryEditRequest;
import com.computershop.DTO.Category.CategoryRequest;
import com.computershop.DTO.Category.CategoryResponse;
import com.computershop.Exception.NotFoundException;
import com.computershop.Mapper.CategoryMapper;
import com.computershop.Model.Entity.Category;
import com.computershop.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not found")  );
        return categoryMapper.toResponse(category);
    }

    public List<Category> filter(Long id, String name, String code, LocalDateTime startDate, LocalDateTime endDate, String sortBy, String sortAs) {
        //  filter or search
        Specification<Category> spec = Specification.unrestricted();
        if (id != null) {
            // filter by id : select * from category where id = ?id
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("id"),id)
                    );
        }
        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("name")),"%"+name.toLowerCase()+"%")
                    );
        }
        if (code != null && !code.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("code")),"%"+code.toLowerCase()+"%")
                    );
        }
        if (startDate != null && endDate != null) {
            spec = spec.and((root, query, cb) ->
                    cb.between(root.get("createAt"),startDate,endDate)
                    );
        }

        // sort
        Sort sort = Sort.by(Sort.Order.desc("id")); // default sort
        if (sortBy !=null && sortAs !=null) {
            sort = sortAs.equalsIgnoreCase("desc") ?
                    Sort.by(Sort.Order.desc(sortBy)) : Sort.by(Sort.Order.asc(sortBy));
        }
        return categoryRepository.findAll(spec,sort);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByCode(categoryRequest.getCode())){
            throw new RuntimeException("Category code exists.");
        }
        Category category = categoryMapper.toEntity(categoryRequest);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    public CategoryResponse update(Long id, CategoryEditRequest payload) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found"));

        category.setCode(payload.getCode());
        category.setName(payload.getName());
        category.setDescription(payload.getDescription());
        category.setStatus(payload.getStatus());
        category.setUpdateBy(payload.getUpdateBy());
        Category updated = categoryRepository.save(category);
        return categoryMapper.toResponse(updated);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        categoryRepository.delete(category);
    }
}
