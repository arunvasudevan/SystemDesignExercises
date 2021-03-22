package com.avasudevan.employeemanagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private final String name;

    private final int id;

    private final List<Employee> subordinates;

    static int empId = 1;

    public Employee(String name) {
        this.name = name;
        this.id = empId++;
        this.subordinates = new ArrayList<>();
    }

    public void addSubordinates(List<Employee> subordinatesList) {
        subordinates.addAll(subordinatesList);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }
}
