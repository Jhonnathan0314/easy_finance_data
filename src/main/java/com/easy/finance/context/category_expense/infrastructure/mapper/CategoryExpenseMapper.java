package com.easy.finance.context.category_expense.infrastructure.mapper;

import com.easy.finance.context.category_expense.application.dto.CategoryExtenseResponseDto;
import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.infrastructure.persistence.CategoryExpenseEntity;
import com.easy.finance.utils.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryExpenseMapper implements Mapper<CategoryExpense, CategoryExpenseEntity, CategoryExtenseResponseDto> {

    @Override
    public CategoryExpense entityToModel(CategoryExpenseEntity entity) {
        return CategoryExpense.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .acronym(entity.getAcronym())
                .state(entity.getState())
                .build();
    }

    @Override
    public CategoryExpenseEntity modelToEntity(CategoryExpense model) {
        return CategoryExpenseEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .acronym(model.getAcronym())
                .state(model.getState())
                .build();
    }

    @Override
    public CategoryExtenseResponseDto modelToDto(CategoryExpense model) {
        return CategoryExtenseResponseDto.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .acronym(model.getAcronym())
                .state(model.getState())
                .build();
    }

    @Override
    public CategoryExpense dtoToModel(CategoryExtenseResponseDto dto) {
        return null;
    }

    @Override
    public List<CategoryExpense> entitiesToModels(List<CategoryExpenseEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryExtenseResponseDto> modelsToDtos(List<CategoryExpense> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }
}
