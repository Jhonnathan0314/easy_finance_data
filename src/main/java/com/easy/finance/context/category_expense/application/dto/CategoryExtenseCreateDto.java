package com.easy.finance.context.category_expense.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExtenseCreateDto {

    private String name;
    private String description;
    private String acronym;
}
