package com.computershop.DTO.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    private String name;
    private String code;
    private String description;
    private Boolean status;
    private String createBy;
    private String updateBy;
}
