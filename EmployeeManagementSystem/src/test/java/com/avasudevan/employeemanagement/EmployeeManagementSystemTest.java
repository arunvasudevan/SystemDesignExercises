package com.avasudevan.employeemanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmployeeManagementSystemTest {

    EmployeeManagementSystem employeeManagementSystem;
    Employee employee1, employee2, employee3;

    @Before
    public void init() {
        List<Employee> employees = new ArrayList<>();
        employee1 = new Employee("flik");
        employee2 = new Employee( "dot");
        employee3 = new Employee( "Princess Atta");
        employee3.addSubordinates(Arrays.asList(employee1, employee2));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employeeManagementSystem = new EmployeeManagementSystem(employees);
    }

    @Test
    public void testGetEmployeeDetailsById() {
        System.out.println("Test Get Employees by ID.....");
        System.out.println(employeeManagementSystem.getEmployeeDetails(employee1.getId()).getName());
        System.out.println(employeeManagementSystem.getEmployeeDetails(employee1.getId()).getId());
    }

    @Test
    public void testGetEmployeeDetailsByName() {
        System.out.println("Test Get Employees by Name.....");
        System.out.println(employeeManagementSystem.getEmployeeDetails("dot").getName());
        System.out.println(employeeManagementSystem.getEmployeeDetails("dot").getId());
    }

    @Test
    public void testSearchEmployeeNamePrefix() {
        System.out.println("Test Employee Name Prefix Search");
        System.out.println(employeeManagementSystem.searchPrefix("fl").getName());
    }

    @Test
    public void testGetAllSubordinatesById() {
        System.out.println("Test Get all Subordinates By Id....");
        employeeManagementSystem.getAllSubOrdinates(employee3.getId()).forEach(employee -> {
            System.out.println(employee.getName());
        });
    }

    @Test
    public void testGetAllSubordinatesByName() {
        System.out.println("Test Get all Subordinates By Name....");
        employeeManagementSystem.getAllSubOrdinates("Princess Atta").forEach(employee -> {
            System.out.println(employee.getName());
        });
    }

}
