package com.cisco.prj.repo;

import com.cisco.prj.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements  ProductDao{
    final static String URL = "jdbc:mysql://localhost:3306/AD_JAVA";
    final static String DRIVER = "com.mysql.cj.jdbc.Driver"; // org.oracle.Driver
    final static String USER = "root";
    final static String PWD = "Welcome123";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product p) {
        String SQL = "INSERT INTO products (id, name, price) VALUES(0, ?, ?)"; // IN parameter
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PWD);
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.executeUpdate(); // INSERT, UPDATE and DELETE
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if( con!= null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        String SQL = "SELECT id, name, price FROM products"; // No IN parameter
        List<Product> products = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PWD);
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(SQL); // SELECT
            while (rs.next()) {
                Product p = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if( con!= null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return products;
    }
}
