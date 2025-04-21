package com.example.demo.service;

import com.example.demo.repo.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoMongoImpl")
    private EmployeeDao employeeDao; // wiring is done by spring container

    public void insert() {
        this.employeeDao.addEmployee();
    }
}
