package com.example.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
@Profile("prod")
public class EmployeeDaoDbImpl implements EmployeeDao{
    @Autowired
    DataSource ds; // pool of DB connections

    @Override
    public void addEmployee() {
        try {
            Connection con = ds.getConnection(); // get connection from pool
            System.out.println(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println();
        System.out.println("Stored in database!!!");
    }
}
