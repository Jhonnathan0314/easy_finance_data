package com.easy.finance.context.category_expense.domain.port;

import com.easy.finance.context.category_expense.domain.model.CategoryExpense;

import java.util.List;
import java.util.Optional;

public interface CategoryExpenseRepository {
    List<CategoryExpense> findAll();
    Optional<CategoryExpense> findById(Long id);

    CategoryExpense create (CategoryExpense categoryExpense);
    CategoryExpense update (CategoryExpense categoryExpense);

    void deleteById(Long id);

}
