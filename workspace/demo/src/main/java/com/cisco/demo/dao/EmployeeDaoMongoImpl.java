package com.cisco.demo.dao;

import com.cisco.demo.entity.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class EmployeeDaoMongoImpl implements EmployeeDao{
    @Override
    public void addEmployee(Employee e) {
        System.out.println(e.getName() + " stored in mongoDB");
    }
}
