package com.easy.finance.context.category_expense.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryExpenseJpaRepository extends JpaRepository<CategoryExpenseEntity, Long> {
}
