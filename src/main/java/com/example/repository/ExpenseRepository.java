package com.example.repository;

import com.example.model.Expense;

import java.util.Collection;

public interface ExpenseRepository<V> extends Repository<Expense> {
    Collection<? extends Expense> filterDataByCategory(V category);
}
