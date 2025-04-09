package com.easy.finance.context.category_expense.application.usecase;

import com.easy.finance.context.category_expense.domain.port.CategoryExpenseRepository;
import com.easy.finance.utils.constants.ErrorMessages;
import com.easy.finance.utils.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteByIdCategoryExpense {

    private final CategoryExpenseRepository categoryExpenseRepository;

    public void deleteById(Long id) throws InvalidRequestException {

        if(!categoryExpenseRepository.findById(id).isPresent()) {
            throw new InvalidRequestException(ErrorMessages.ID_NO_EXIST);
        }
        categoryExpenseRepository.deleteById(id);
    }
}
