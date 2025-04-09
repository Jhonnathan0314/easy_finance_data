package com.easy.finance.context.category_expense.application.usecase;

import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.domain.port.CategoryExpenseRepository;
import com.easy.finance.utils.constants.ErrorMessages;
import com.easy.finance.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllCategoryExpense {

    private final CategoryExpenseRepository categoryExpenseRepository;

    public List<CategoryExpense> findAll() throws NoResultsException {
        List<CategoryExpense> categoryExpenses = categoryExpenseRepository.findAll();
        if(categoryExpenses.isEmpty()) {
            throw new NoResultsException(ErrorMessages.NO_RESULTS);
        }
        return categoryExpenses;
    }
}
