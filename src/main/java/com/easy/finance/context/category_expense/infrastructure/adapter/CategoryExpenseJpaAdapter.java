package com.easy.finance.context.category_expense.infrastructure.adapter;

import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.domain.port.CategoryExpenseRepository;
import com.easy.finance.context.category_expense.infrastructure.mapper.CategoryExpenseMapper;
import com.easy.finance.context.category_expense.infrastructure.persistence.CategoryExpenseEntity;
import com.easy.finance.context.category_expense.infrastructure.persistence.CategoryExpenseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryExpenseJpaAdapter implements CategoryExpenseRepository {

    private final CategoryExpenseJpaRepository categoryExpenseJpaRepository;

    private final CategoryExpenseMapper categoryExpenseMapper = new CategoryExpenseMapper();

    @Override
    public List<CategoryExpense> findAll() {
        return categoryExpenseMapper.entitiesToModels(categoryExpenseJpaRepository.findAll());
    }

    @Override
    public Optional<CategoryExpense> findById(Long id) {
        Optional<CategoryExpenseEntity> categoryExpenseEntity = categoryExpenseJpaRepository.findById(id);
        return categoryExpenseEntity.map(categoryExpenseMapper:: entityToModel);
    }

    @Override
    public CategoryExpense create(CategoryExpense categoryExpense) {
        CategoryExpenseEntity entity = categoryExpenseMapper.modelToEntity(categoryExpense);
        return categoryExpenseMapper.entityToModel(categoryExpenseJpaRepository.save(entity));
    }

    @Override
    public CategoryExpense update(CategoryExpense categoryExpense) {
        CategoryExpenseEntity entity = categoryExpenseMapper.modelToEntity(categoryExpense);
        return categoryExpenseMapper.entityToModel(categoryExpenseJpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        categoryExpenseJpaRepository.deleteById(id);
    }
}
