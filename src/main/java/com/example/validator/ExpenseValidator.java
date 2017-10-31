package com.example.validator;

import com.example.model.Expense;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("ExpenseValidator")
public class ExpenseValidator implements Validator<Expense> {

    @Override
    public boolean validate(Expense expense) {
        String expenseName = expense.getName();
        BigDecimal expensePrice = expense.getPrice();
        String expenseCategory = expense.getCategory();
        if (expenseName == null || expenseName.equals("")) {
            return false;
        } else if (expensePrice == null || expensePrice.equals(BigDecimal.ZERO)) {
            return false;
        } else if (expenseCategory == null || expenseCategory.equals("")) {
            return false;
        }
        return true;
    }
}
