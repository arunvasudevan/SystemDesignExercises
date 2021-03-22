package com.avasudevan.employeemanagement;

import java.util.List;
import java.util.Optional;

public class EmployeeManagementSystem {

    List<Employee> employees;


    public EmployeeManagementSystem(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee searchPrefix(String name) {
        return employees.stream().filter(employee -> {
            if(employee.getName().startsWith(name)) {
                return true;
            }
            return false;
        }).findFirst().get();
    }

    public Employee getEmployeeDetails(String name) {
        Optional<Employee> optionalEmployee = employees.stream().filter(employee -> employee.getName().equalsIgnoreCase(name)).findFirst();
        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        return null;
    }

    public Employee getEmployeeDetails(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst().get();
    }

    public List<Employee> getAllSubOrdinates(String name) {
        Employee emp = getEmployeeDetails(name);

        if(emp != null) {
            return emp.getSubordinates();
        }
        return null;
    }

    public List<Employee> getAllSubOrdinates(int id) {
        Employee emp = getEmployeeDetails(id);

        if(emp != null) {
            return emp.getSubordinates();
        }
        return null;
    }
}
