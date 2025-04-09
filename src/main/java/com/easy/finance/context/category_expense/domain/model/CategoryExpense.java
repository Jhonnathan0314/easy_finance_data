package com.easy.finance.context.category_expense.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExpense {

    private Long id;
    private String name;
    private String description;
    private String acronym;
    private String state;

    public boolean isValid(){
        if(name == null || description == null || acronym == null){
            return false;
        }
        if(name.isEmpty() || description.isEmpty() || acronym.isEmpty()){
            return false;
        }
        return true;
    }

}
