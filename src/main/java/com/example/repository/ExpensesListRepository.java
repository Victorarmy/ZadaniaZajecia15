package com.example.repository;

import com.example.model.Expense;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("ExpenseRepositoryOnList")
public class ExpensesListRepository implements ExpenseRepository<String> {

    private List<Expense> expenseList;

    public ExpensesListRepository() {
        this.expenseList = new ArrayList<>();
    }

    public boolean add(Expense expense) {
        return expenseList.add(expense);
    }

    public List<Expense> getAll() {
        return expenseList;
    }

    public List<Expense> filterDataByCategory(String category) {
        return expenseList
                .stream()
                .filter(expense -> expense.getCategory()
                                          .toLowerCase()
                                          .equals(category.toLowerCase()))
                .collect(Collectors.toList());
    }
}
