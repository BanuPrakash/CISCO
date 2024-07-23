package com.cisco.demo.dao;

import com.cisco.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class EmployeeDaoJdbcImpl implements  EmployeeDao{
    @Autowired
    DataSource ds;
    @Override
    public void addEmployee(Employee e) {
        try {
            System.out.println(ds.getConnection());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(e.getName() + " stored in Database!!!");
    }
}
