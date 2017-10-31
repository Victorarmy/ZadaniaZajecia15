package com.example.utilities;

import com.example.model.Expense;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Qualifier("ExpenseSortUtilitiesOnStream")
public class ExpenseSortUtilities implements SortUtilities<Expense, String> {

    @Override
    public Collection<? extends Expense> getRightOrder(Collection<? extends Expense> collection, String orderIdentifier) {
        ExpenseOrderIdentifier identifier = ExpenseOrderIdentifier.getOrderIdentifier(orderIdentifier);
        if (identifier == null) {
            throw new IllegalArgumentException("Niepoprawny identyfikator sortowania");
        }
        return collection.stream()
                         .sorted(identifier.getComparator())
                         .collect(Collectors.toList());
    }
}
