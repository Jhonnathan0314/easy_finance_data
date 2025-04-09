package com.easy.finance.context.category_expense.infrastructure.mapper;

import com.easy.finance.context.category_expense.application.dto.CategoryExtenseUpdateDto;
import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.infrastructure.persistence.CategoryExpenseEntity;
import com.easy.finance.utils.mapper.Mapper;

import java.util.List;

public class CategoryExpenseUpdateMapper implements Mapper<CategoryExpense, CategoryExpenseEntity, CategoryExtenseUpdateDto> {
    @Override
    public CategoryExpense entityToModel(CategoryExpenseEntity entity) {
        return null;
    }

    @Override
    public CategoryExpenseEntity modelToEntity(CategoryExpense model) {
        return null;
    }

    @Override
    public CategoryExtenseUpdateDto modelToDto(CategoryExpense model) {
        return null;
    }

    @Override
    public CategoryExpense dtoToModel(CategoryExtenseUpdateDto dto) {
        return CategoryExpense.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .acronym(dto.getAcronym())
                .state(dto.getState())
                .build();
    }

    @Override
    public List<CategoryExpense> entitiesToModels(List<CategoryExpenseEntity> entities) {
        return null;
    }

    @Override
    public List<CategoryExtenseUpdateDto> modelsToDtos(List<CategoryExpense> models) {
        return null;
    }
}
