package com.computershop.Mapper;

import com.computershop.DTO.BrandRequest;
import com.computershop.DTO.BrandResponse;
import com.computershop.Model.Entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toEntity(BrandRequest brandRequest) {
        return Brand.builder()
                .name(brandRequest.getName())
                .code(brandRequest.getCode())
                .description(brandRequest.getDescription())
                .status(brandRequest.getStatus() == null || brandRequest.getStatus()) //  .status(brandRequest.getStatus() == null ? true : brandRequest.getStatus())
                .createBy(brandRequest.getCreateBy())
                .updateBy(brandRequest.getUpdateBy())
                .build();
    }
    public BrandResponse toResponse(Brand entity) {
        return BrandResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .status(entity.getStatus() == null || entity.getStatus())
                .createAt(entity.getCreateAt())
                .createBy(entity.getCreateBy())
                .updateBy(entity.getUpdateBy())
                .updateAt(entity.getUpdateAt())
                .build();
    }

}
