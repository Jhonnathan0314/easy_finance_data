package com.easy.finance.context.category_expense.application.usecase;

import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.domain.port.CategoryExpenseRepository;
import com.easy.finance.utils.constants.ErrorMessages;
import com.easy.finance.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByIdCategoryExpense {
    private final CategoryExpenseRepository categoryExpenseRepository;

    public CategoryExpense findById(Long id) throws NoResultsException {
        Optional<CategoryExpense> categoryExpense = categoryExpenseRepository.findById(id);
        if(categoryExpense.isEmpty()) {
            throw new NoResultsException(ErrorMessages.NO_RESULTS);
        }
        return categoryExpense.get();
    }

}
