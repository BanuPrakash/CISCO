package com.cisco.demo.service;

import com.cisco.demo.dao.EmployeeDao;
import com.cisco.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao; // dependency , loosely coupled

    public void insert(Employee e) {
        employeeDao.addEmployee(e);
    }
}
