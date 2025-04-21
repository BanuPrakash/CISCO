package com.example.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoDbImpl implements EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("Stored in database!!!");
    }
}
