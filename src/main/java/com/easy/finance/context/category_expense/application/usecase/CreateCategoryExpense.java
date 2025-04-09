package com.easy.finance.context.category_expense.application.usecase;

import com.easy.finance.context.category_expense.domain.model.CategoryExpense;
import com.easy.finance.context.category_expense.domain.port.CategoryExpenseRepository;
import com.easy.finance.utils.constants.ErrorMessages;
import com.easy.finance.utils.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryExpense {

    private final CategoryExpenseRepository categoryExpenseRepository;

    public CategoryExpense create(CategoryExpense categoryExpense) throws InvalidRequestException {
        if(!categoryExpense.isValid()) {
            throw new InvalidRequestException(ErrorMessages.INCOMPLETE_DATA);
        }
        return categoryExpenseRepository.create(categoryExpense);
    }
}
