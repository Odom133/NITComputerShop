package com.computershop.DTO.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Boolean status;
    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;
}
