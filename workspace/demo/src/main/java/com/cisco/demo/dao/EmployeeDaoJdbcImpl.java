package com.cisco.demo.dao;

import com.cisco.demo.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoJdbcImpl implements  EmployeeDao{
    @Override
    public void addEmployee(Employee e) {
        System.out.println(e.getName() + " stored in Database!!!");
    }
}
