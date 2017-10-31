package com.example.utilities;

import com.example.model.Expense;

import java.util.Comparator;

public enum ExpenseOrderIdentifier {

    CENA_ROSNACO ("CenaRosnaco", Comparator.comparing(Expense::getPrice)),
    CENA_MALEJACO ("CenaMalejaco", CENA_ROSNACO.comparator.reversed()),
    NAZWA_ALFABETYCZNIE ("NazwaAlfabetycznie", Comparator.comparing(Expense::getName)),
    NAZWA_ODWROTNIE("NazwaOdwrotnieAlfabetycznie", NAZWA_ALFABETYCZNIE.comparator.reversed());

    private String requestParamName;
    private Comparator<Expense> comparator;

    ExpenseOrderIdentifier(String requestParamName, Comparator<Expense> comparator) {
        this.requestParamName = requestParamName;
        this.comparator = comparator;
    }

    public static ExpenseOrderIdentifier getOrderIdentifier (String requestParameter) {
        for (ExpenseOrderIdentifier expenseOrderIdentifier : ExpenseOrderIdentifier.values()) {
            if (expenseOrderIdentifier.requestParamName.equals(requestParameter)) {
                return expenseOrderIdentifier;
            }
        }
        return null;
    }

    public Comparator<Expense> getComparator() {
        return comparator;
    }
}
