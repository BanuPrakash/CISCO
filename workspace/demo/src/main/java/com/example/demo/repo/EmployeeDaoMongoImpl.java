package com.example.demo.repo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoMongoImpl implements EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("Mongo Store!!!");
    }
}
