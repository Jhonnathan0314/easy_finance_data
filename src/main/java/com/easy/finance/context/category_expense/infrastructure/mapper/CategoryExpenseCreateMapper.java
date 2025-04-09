package com.easy.finance.context.category_expense.infrastructure.mapper;

import com.easy.finance.context.category_expense.application.dto.CategoryExtenseCreateDto;
import com.easy.finance.context.category_expense.application.dto.CategoryExtenseResponseDto;
import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.infrastructure.persistence.CategoryExpenseEntity;
import com.easy.finance.utils.mapper.Mapper;

import java.util.List;

public class CategoryExpenseCreateMapper implements Mapper<CategoryExpense, CategoryExpenseEntity, CategoryExtenseCreateDto> {
    @Override
    public CategoryExpense entityToModel(CategoryExpenseEntity entity) {
        return null;
    }

    @Override
    public CategoryExpenseEntity modelToEntity(CategoryExpense model) {
        return null;
    }

    @Override
    public CategoryExtenseCreateDto modelToDto(CategoryExpense model) {
        return null;
    }

    @Override
    public CategoryExpense dtoToModel(CategoryExtenseCreateDto dto) {
        return CategoryExpense.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .acronym(dto.getAcronym())
                .build();
    }

    @Override
    public List<CategoryExpense> entitiesToModels(List<CategoryExpenseEntity> entities) {
        return null;
    }

    @Override
    public List<CategoryExtenseCreateDto> modelsToDtos(List<CategoryExpense> models) {
        return null;
    }
}
