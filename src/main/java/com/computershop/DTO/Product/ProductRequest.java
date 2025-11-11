package com.computershop.DTO.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotEmpty
    @NotNull
    private String name;

    private String description;

    @NotNull
    @Min(value = 0, message = "Quantity must be >= 0")
    private Integer quantity;

    private Double price;

    @NotNull
    private Boolean status;

    @NotNull
    private Long brandId;

    @NotNull
    private Long categoryId;

    private String createBy;
    private String updateBy;
}
