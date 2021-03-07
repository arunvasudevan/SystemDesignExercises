package com.avasudevan.splitwise;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int id;
    private final String name;
    private final Map<Integer, Double> oweMap;
    private double totalExpenses;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.oweMap = new HashMap<>();
        this.totalExpenses = 0;
    }

    public Map<Integer, Double> getOweMap() {
        return oweMap;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
