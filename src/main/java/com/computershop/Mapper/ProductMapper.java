package com.computershop.Mapper;

import com.computershop.DTO.Product.ProductRequest;
import com.computershop.DTO.Product.ProductResponse;
import com.computershop.Model.Entity.Brand;
import com.computershop.Model.Entity.Category;
import com.computershop.Model.Entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest dto, Category category, Brand brand) {

        return Product.builder()

                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                .category(category)
                .brand(brand)
                .createBy(dto.getCreateBy())
                .updateBy(dto.getUpdateBy())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getStatus())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .brandId(product.getBrand().getId())
                .brandName(product.getBrand().getName())
                .build();
    }
}
