package com.example.model;

import java.math.BigDecimal;

public class Expense {

    private String name;
    private BigDecimal price;
    private String category;

    public Expense(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
