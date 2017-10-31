package com.example.controller;

import com.example.model.Expense;
import com.example.repository.ExpenseRepository;
import com.example.utilities.SortUtilities;
import com.example.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

@Controller
public class ExpenseController {

    private ExpenseRepository expensesRepository;
    private Validator validator;
    private SortUtilities sortUtilities;

    @Autowired
    public ExpenseController(@Qualifier("ExpenseRepositoryOnList") ExpenseRepository expensesRepository, @Qualifier("ExpenseValidator") Validator validator, @Qualifier("ExpenseSortUtilitiesOnStream") SortUtilities sortUtilities) {
        this.expensesRepository = expensesRepository;
        this.validator = validator;
        this.sortUtilities = sortUtilities;
    }

    @GetMapping("/wydatki")
    @ResponseBody
    public String expensesList(@RequestParam(name = "kategoria", required = false) String category, @RequestParam(name = "sortowanie", required = false) String sortType) {

        String response = "";
        BigDecimal totalPrice = BigDecimal.valueOf(0D);
        Collection<Expense> expenseCollection = expensesRepository.getAll();

        if (category != null) {
            expenseCollection = (expensesRepository.filterDataByCategory(category));
            if (expenseCollection.isEmpty()) {
                return "Nie ma produktów danej kategorii";
            }
        }

        if (sortType != null) {
            try {
                expenseCollection = sortUtilities.getRightOrder(expenseCollection, sortType);
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }

        for (Expense expense : expenseCollection) {
            response += expense.toString() + "<br/>";
            totalPrice = totalPrice.add(expense.getPrice());
        }

        response += "Total price = " + totalPrice.toPlainString();
        return response;
    }

    @PostMapping("/addNewExpense")
    public String addNewExpense(@RequestParam String name, @RequestParam BigDecimal price, @RequestParam String category) {
        Expense expense = new Expense(name, price, category);
        if (validator.validate(expense)) {
            expensesRepository.add(expense);
            return "redirect:/wydatki";
        } else {
            return "redirect:/bledneDane.html";
        }
    }
}
