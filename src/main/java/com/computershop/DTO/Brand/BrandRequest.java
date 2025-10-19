package com.computershop.DTO.Brand;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {
    @NotEmpty(message = "Code required!")
    private String code;
    @NotEmpty(message = "Name required!")
    private String name;
    private String description;
    private Boolean status;
    private String createBy;
    private String updateBy;
}
