package com.computershop.DTO.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEditRequest {

    @NotEmpty(message = "Code required!")
    private String code;
    @NotEmpty(message = "Name required!")
    private String name;
    private String description;
    private Boolean status;
    private String updateBy;
}
