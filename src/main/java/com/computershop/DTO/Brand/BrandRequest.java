package com.computershop.DTO.Brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {
    private String name;
    private String code;
    private String description;
    private Boolean status;
    private String createBy;
    private String updateBy;
}
