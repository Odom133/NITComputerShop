package com.computershop.Mapper;

import com.computershop.DTO.Category.CategoryRequest;
import com.computershop.DTO.Category.CategoryResponse;
import com.computershop.Model.Entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest categoryRequest) {
        return Category.builder()
                .code(categoryRequest.getCode())
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .status(categoryRequest.getStatus())
                .createBy(categoryRequest.getCreateBy())
                .updateBy(categoryRequest.getUpdateBy())
                .build();
    }

    public CategoryResponse toResponse(Category entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createAt(entity.getCreateAt())
                .createBy(entity.getCreateBy())
                .updateAt(entity.getUpdateAt())
                .updateBy(entity.getUpdateBy())
                .build();
    }
}
